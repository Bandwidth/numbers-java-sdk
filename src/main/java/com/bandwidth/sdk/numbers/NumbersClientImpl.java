package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.exception.ExceptionUtils;
import com.bandwidth.sdk.numbers.helpers.RetryableRequest;
import com.bandwidth.sdk.numbers.helpers.SleepRetryPolicy;
import com.bandwidth.sdk.numbers.models.AvailableNumberSearchRequest;
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
import java.util.concurrent.CompletableFuture;

import static com.bandwidth.sdk.numbers.exception.ExceptionUtils.catchAsyncClientExceptions;
import static com.bandwidth.sdk.numbers.exception.ExceptionUtils.validateOrderResponse;
import static org.asynchttpclient.Dsl.asyncHttpClient;

/**
 * @see NumbersClient
 */
public class NumbersClientImpl implements NumbersClient {

   private static final SleepRetryPolicy SLEEP_RETRY_POLICY = new SleepRetryPolicy();

   private final String account;
   private final String baseUrl;
   private final AsyncHttpClient httpClient;

   NumbersClientImpl(
      String baseUrl,
      String account,
      AsyncHttpClient httpClient) {
      this.baseUrl = baseUrl;
      this.account = account;
      this.httpClient = httpClient;
   }

   @Override
   public SearchResult getAvailableTelephoneNumbers(AvailableNumberSearchRequest availableNumberSearchRequest) {
      return getAvailableTelephoneNumbersAsync(availableNumberSearchRequest).join();
   }

   private CompletableFuture<SearchResult> getAvailableTelephoneNumbersAsync(AvailableNumberSearchRequest availableNumberSearchRequest) {
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
   public OrderResponse orderTelephoneNumbers(Order order) {
      OrderResponse initialOrder = validateOrderResponse(() -> orderTelephoneNumbersAsync(order).join());

      return RetryableRequest.executeRequest(
         () -> getOrderStatus(initialOrder.getOrder().getId()),
         OrderResponse::isTerminal,
         SLEEP_RETRY_POLICY);
   }

   private CompletableFuture<OrderResponse> orderTelephoneNumbersAsync(Order order) {
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

   private OrderResponse getOrderStatus(String orderId) {
      return validateOrderResponse(() -> {
         String url = MessageFormat.format("{0}/accounts/{1}/orders/{2}", baseUrl, account, orderId);
         return httpClient.prepareGet(url)
            .execute()
            .toCompletableFuture()
            .thenApply(resp -> {
               String responseBodyString = resp.getResponseBody(StandardCharsets.UTF_8);
               return NumbersSerde.deserialize(responseBodyString, OrderResponse.class);
            })
            .join();
      });
   }

   @Override
   public void close() {
      try {
         this.httpClient.close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static NumbersClientImpl.Builder builder() {
      return new NumbersClientImpl.Builder();
   }

   public static class Builder {

      private static final String X_REALM_HEADER_NAME = "x-realm";
      private static final String X_REALM_HEADER_VALUE = "admin";

      /**
       * {@link RequestFilter} that adds the required "x-realm: admin" header to all outbound requests.
       */
      private static final RequestFilter REALM_HEADER_FILTER = new RequestFilter() {
         @Override
         public <T> FilterContext<T> filter(FilterContext<T> ctx) {
            HttpHeaders headers = ctx.getRequest().getHeaders();
            headers.add(X_REALM_HEADER_NAME, X_REALM_HEADER_VALUE);
            return ctx;
         }
      };

      private static final String DEFAULT_BASE_URL = "https://dashboard.bandwidth.com/api";
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

      /**
       * Mandatory. Specify the id for the account that the client will target.
       */
      public Builder account(String account) {
         this.account = account;
         return this;
      }

      /**
       * Mandatory. Specify the username for the account that the client will target.
       */
      public Builder username(String username) {
         this.username = username;
         return this;
      }

      /**
       * Mandatory. Specify the password for the account that the client will target.
       */
      public Builder password(String password) {
         this.password = password;
         return this;
      }

      /**
       * Change the base URL to be something other than the Bandwidth Numbers production API. Only used for internal
       * testing purposes.
       */
      public Builder baseUrl(String baseUrl) {
         this.baseUrl = baseUrl;
         return this;
      }

      /**
       * Optional. Allows specifying a {@link AsyncHttpClientConfig} with custom settings. The passed configuration will
       * be cloned and the necessary configuration for the Numbers client will be added.
       */
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
