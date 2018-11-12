package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.models.SearchResult;
import com.bandwidth.sdk.numbers.models.orders.Order;
import com.bandwidth.sdk.numbers.models.orders.OrderResponse;
import com.bandwidth.sdk.numbers.requests.AvailableNumberSearchRequest;

import java.util.concurrent.CompletableFuture;

public interface NumbersClient extends AutoCloseable {

   //TODO: Is it really worth it to have these non-async methods? The caller can just call join() themselves

   SearchResult getAvailableTelephoneNumbers(AvailableNumberSearchRequest availableNumberSearchRequest);
   CompletableFuture<SearchResult> getAvailableTelephoneNumbersAsync(AvailableNumberSearchRequest availableNumberSearchRequest);
   OrderResponse orderTelephoneNumbers(Order order);
   CompletableFuture<OrderResponse> orderTelephoneNumbersAsync(Order order);
}
