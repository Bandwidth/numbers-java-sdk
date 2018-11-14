package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.models.SearchResult;
import com.bandwidth.sdk.numbers.models.orders.Order;
import com.bandwidth.sdk.numbers.models.orders.OrderResponse;
import com.bandwidth.sdk.numbers.models.AvailableNumberSearchRequest;

public interface NumbersClient extends AutoCloseable {
   SearchResult getAvailableTelephoneNumbers(AvailableNumberSearchRequest availableNumberSearchRequest);
   OrderResponse orderTelephoneNumbers(Order order);
}
