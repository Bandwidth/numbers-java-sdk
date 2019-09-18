package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.exception.NumbersApiException;
import com.bandwidth.sdk.numbers.exception.NumbersClientException;
import com.bandwidth.sdk.numbers.models.AvailableNumberSearchRequest;
import com.bandwidth.sdk.numbers.models.ImmutableErrorResponse;
import com.bandwidth.sdk.numbers.models.ImmutableSearchResult;
import com.bandwidth.sdk.numbers.models.SearchResult;
import com.bandwidth.sdk.numbers.models.orders.ExistingTelephoneNumberOrderType;
import com.bandwidth.sdk.numbers.models.orders.ImmutableOrderResponse;
import com.bandwidth.sdk.numbers.models.orders.Order;
import com.bandwidth.sdk.numbers.models.orders.OrderResponse;
import com.bandwidth.sdk.numbers.serde.NumbersSerde;
import org.assertj.core.api.Assertions;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NumbersClientImplTest {

   private NumbersClient numbersClient;

   private AvailableNumberSearchRequest availableNumberSearchRequest = AvailableNumberSearchRequest.builder()
      .state("NC")
      .city("RALEIGH")
      .quantity(5)
      .tollFreeWildCardPattern("8**")
      .build();

   private SearchResult searchResult = ImmutableSearchResult.builder()
      .resultCount(1)
      .addTelephoneNumberList("5555555555")
      .build();

   private ExistingTelephoneNumberOrderType existingTelephoneNumberOrderType = ExistingTelephoneNumberOrderType.builder()
      .addTelephoneNumberList("5555555555")
      .build();

   private Order order = Order.builder()
      .siteId("1")
      .peerId("1")
      .existingTelephoneNumberOrderType(existingTelephoneNumberOrderType)
      .id("foo")
      .build();

   private OrderResponse completedOrderResponse = ImmutableOrderResponse.builder()
      .order(order)
      .orderStatus(OrderResponse.OrderStatus.COMPLETE)
      .build();

   private OrderResponse incompleteOrderResponse = ImmutableOrderResponse.builder()
      .order(order)
      .orderStatus(OrderResponse.OrderStatus.RECEIVED)
      .build();

   private OrderResponse errorOrderResponse = ImmutableOrderResponse.builder()
      .addErrorList(ImmutableErrorResponse.builder()
         .code("1")
         .description("error")
         .build())
      .order(order)
      .build();

   @Mock
   private AsyncHttpClient asyncHttpClient;
   @Mock
   private BoundRequestBuilder boundRequestBuilder;
   @Mock
   private ListenableFuture<Response> listenableFuture;
   @Mock
   private Response response;

   @Before
   public void onBefore() {
      numbersClient = new NumbersClientImpl("base", "account", asyncHttpClient);
   }

   @Test
   public void searchingForNumbers() {
      when(asyncHttpClient.prepareGet(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setQueryParams(anyList())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.execute()).thenReturn(listenableFuture);
      when(listenableFuture.toCompletableFuture()).thenReturn(CompletableFuture.completedFuture(response));
      when(response.getResponseBody(StandardCharsets.UTF_8)).thenReturn(NumbersSerde.serialize(searchResult));
      assertThat(searchResult).isEqualTo(numbersClient.getAvailableTelephoneNumbers(availableNumberSearchRequest));
   }

   @Test
   public void searchingForNumbersExceptionThrowsClientException() {
      when(asyncHttpClient.prepareGet(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setQueryParams(anyList())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.execute()).thenReturn(listenableFuture);
      when(listenableFuture.toCompletableFuture()).thenReturn(CompletableFuture.completedFuture(response));
      when(response.getResponseBody(StandardCharsets.UTF_8)).thenReturn(NumbersSerde.serialize("invalid"));

      Throwable thrown = Assertions.catchThrowable(() ->
         numbersClient.getAvailableTelephoneNumbers(availableNumberSearchRequest));

      assertThat(thrown).isInstanceOf(CompletionException.class).hasCauseInstanceOf(NumbersClientException.class);
   }

   @Test
   public void searchingForNumbersWithAsyncClientException() {
      when(asyncHttpClient.prepareGet(anyString())).thenThrow(new RuntimeException());
      Throwable thrown = Assertions.catchThrowable(() ->
         numbersClient.getAvailableTelephoneNumbers(availableNumberSearchRequest));

      assertThat(thrown).isInstanceOf(CompletionException.class).hasCauseInstanceOf(NumbersClientException.class);
   }

   @Test
   public void orderingNumbers() {
      when(asyncHttpClient.preparePost(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setBody(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setHeader(any(CharSequence.class), anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.execute()).thenReturn(listenableFuture);
      when(listenableFuture.toCompletableFuture()).thenReturn(CompletableFuture.completedFuture(response));
      when(response.getResponseBody(StandardCharsets.UTF_8)).thenReturn(NumbersSerde.serialize(incompleteOrderResponse), NumbersSerde.serialize(completedOrderResponse));

      when(asyncHttpClient.prepareGet(anyString())).thenReturn(boundRequestBuilder);

      assertThat(completedOrderResponse).isEqualTo(numbersClient.orderTelephoneNumbers(order));
      verify(asyncHttpClient, times(1)).preparePost(anyString());
      verify(asyncHttpClient, times(1)).prepareGet(anyString());
   }

   @Test
   public void orderingNumberWithPolling() {
      when(asyncHttpClient.preparePost(anyString())).thenReturn(boundRequestBuilder);
      when(asyncHttpClient.prepareGet(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setBody(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setHeader(any(CharSequence.class), anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.execute()).thenReturn(listenableFuture);
      when(listenableFuture.toCompletableFuture()).thenReturn(CompletableFuture.completedFuture(response));

      String incompleteResponseString = NumbersSerde.serialize(incompleteOrderResponse);
      when(response.getResponseBody(StandardCharsets.UTF_8)).thenReturn(
         incompleteResponseString,
         incompleteResponseString,
         NumbersSerde.serialize(completedOrderResponse));

      assertThat(completedOrderResponse).isEqualTo(numbersClient.orderTelephoneNumbers(order));
      verify(asyncHttpClient, times(1)).preparePost(anyString());
      verify(asyncHttpClient, times(2)).prepareGet(anyString());
   }

   @Test
   public void orderNumbersWithError() {
      when(asyncHttpClient.preparePost(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setBody(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setHeader(any(CharSequence.class), anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.execute()).thenReturn(listenableFuture);
      when(listenableFuture.toCompletableFuture()).thenReturn(CompletableFuture.completedFuture(response));

      when(response.getResponseBody(StandardCharsets.UTF_8)).thenReturn(
         NumbersSerde.serialize(errorOrderResponse));

      Throwable thrown = Assertions.catchThrowable(() -> numbersClient.orderTelephoneNumbers(order));

      assertThat(thrown).isInstanceOf(NumbersApiException.class);
   }

}
