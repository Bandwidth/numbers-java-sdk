package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.exception.ExceptionUtils;
import com.bandwidth.sdk.numbers.helpers.RetryableRequest;
import com.bandwidth.sdk.numbers.helpers.SleepRetryPolicy;
import com.bandwidth.sdk.numbers.models.AvailableNumberSearchRequest;
import com.bandwidth.sdk.numbers.models.ErrorResponse;
import com.bandwidth.sdk.numbers.models.SearchResult;
import com.bandwidth.sdk.numbers.models.orders.Order;
import com.bandwidth.sdk.numbers.models.orders.OrderResponse;
import com.bandwidth.sdk.numbers.serde.NumbersSerde;
import com.google.common.base.Preconditions;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Realm;
import org.asynchttpclient.filter.FilterContext;
import org.asynchttpclient.filter.RequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.bandwidth.sdk.numbers.exception.ExceptionUtils.catchAsyncClientExceptions;
import static org.asynchttpclient.Dsl.asyncHttpClient;

public class NumbersClientImpl implements NumbersClient {

   private static final SleepRetryPolicy SLEEP_RETRY_POLICY = new SleepRetryPolicy();

   private final String account;
   private final String baseUrl;
   private final AsyncHttpClient httpClient;

   private NumbersClientImpl(
      String baseUrl,
      String account,
      AsyncHttpClient httpClient) {
      this.baseUrl = baseUrl;
      this.account = account;
      this.httpClient = httpClient;
   }

   @Override
   public OrderResponse orderTelephoneNumbers(Order order) {
      OrderResponse initialOrder = orderTelephoneNumbersAsync(order).join();

      List<ErrorResponse> errorList = initialOrder.getErrorList();
      if (errorList != null && !errorList.isEmpty()) {
         throw ExceptionUtils.consolidateApiErrors(errorList);
      }

      return RetryableRequest.executeRequest(
         () -> getOrderStatus(initialOrder.getOrder().getId()), OrderResponse::isTerminal, SLEEP_RETRY_POLICY);
   }

   public OrderResponse getOrderStatus(String orderId) {
      String url = MessageFormat.format("{0}/accounts/{1}/orders/{2}", baseUrl, account, orderId);
      return httpClient.prepareGet(url)
         .execute()
         .toCompletableFuture()
         .thenApply(resp -> {
            String responseBodyString = resp.getResponseBody(StandardCharsets.UTF_8);
            return NumbersSerde.deserialize(responseBodyString, OrderResponse.class);
         })
         .join();
   }

   public CompletableFuture<OrderResponse> orderTelephoneNumbersAsync(Order order) {
      return catchAsyncClientExceptions(() -> {
         String url = MessageFormat.format("{0}/accounts/{1}/orders", baseUrl, account);
         return httpClient.preparePost(url)
            .setHeader(HttpHeaderNames.CONTENT_TYPE, "application/xml")
            .setBody(NumbersSerde.serialize(order))
            .execute()
            .toCompletableFuture()
            .thenApply(resp -> {
               String responseBodyString = resp.getResponseBody(StandardCharsets.UTF_8);
               return NumbersSerde.deserialize(responseBodyString, OrderResponse.class);
            });
      });
   }

   @Override
   public SearchResult getAvailableTelephoneNumbers(AvailableNumberSearchRequest availableNumberSearchRequest) {
      return getAvailableTelephoneNumbersAsync(availableNumberSearchRequest).join();
   }

   public CompletableFuture<SearchResult> getAvailableTelephoneNumbersAsync(AvailableNumberSearchRequest availableNumberSearchRequest) {
      return ExceptionUtils.catchAsyncClientExceptions(() -> {
         String url = MessageFormat.format("{0}/accounts/{1}/availableNumbers", baseUrl, account);
         return httpClient.prepareGet(url)
            .setQueryParams(availableNumberSearchRequest.toParams())
            .execute()
            .toCompletableFuture()
            .thenApply(resp -> {
               String responseBodyString = resp.getResponseBody(StandardCharsets.UTF_8);
               return NumbersSerde.deserialize(responseBodyString, SearchResult.class);
            });
      });
   }

   @Override
   public void close() throws IOException {
      this.httpClient.close();
   }

   public static NumbersClientImpl.Builder builder() {
      return new NumbersClientImpl.Builder();
   }

   private static class Builder {

      private static final String X_REALM_HEADER_NAME = "x-realm";
      private static final String X_REALM_HEADER_VALUE = "admin";

      /**
       * {@link RequestFilter} that adds the required "x-realm: admin" header to all outbound requests
       */
      private static final RequestFilter REALM_HEADER_FILTER = new RequestFilter() {
         @Override
         public <T> FilterContext<T> filter(FilterContext<T> ctx) {
            final HttpHeaders headers = ctx.getRequest().getHeaders();
            headers.add(X_REALM_HEADER_NAME, X_REALM_HEADER_VALUE);
            return ctx;
         }
      };

      //TODO: Specify the real production base URL here
      private static final String DEFAULT_BASE_URL = "TODO";
      private static final DefaultAsyncHttpClientConfig DEFAULT_CONFIG = new DefaultAsyncHttpClientConfig.Builder().build();

      private String account;
      private String username;
      private String password;
      private String baseUrl;
      private AsyncHttpClientConfig config;

      private Builder() {
         this.baseUrl = DEFAULT_BASE_URL;
         this.config = DEFAULT_CONFIG;
      }

      public Builder account(String account) {
         this.account = account;
         return this;
      }

      public Builder username(String username) {
         this.username = username;
         return this;
      }

      public Builder password(String password) {
         this.password = password;
         return this;
      }

      public Builder baseUrl(String baseUrl) {
         this.baseUrl = baseUrl;
         return this;
      }

      public Builder config(AsyncHttpClientConfig config) {
         this.config = config;
         return this;
      }

      public NumbersClientImpl build() {
         Preconditions.checkNotNull(account, "An account must be specified!");
         Preconditions.checkNotNull(username, "A username must be specified!");
         Preconditions.checkNotNull(password, "A password must be specified!");

         AsyncHttpClientConfig httpClientConfig = new DefaultAsyncHttpClientConfig.Builder(config)
            .setRealm(new Realm.Builder(username, password)
               .setUsePreemptiveAuth(true)
               .setScheme(Realm.AuthScheme.BASIC))
            .addRequestFilter(REALM_HEADER_FILTER)
            .build();

         return new NumbersClientImpl(baseUrl, account, asyncHttpClient(httpClientConfig));
      }
   }
}
