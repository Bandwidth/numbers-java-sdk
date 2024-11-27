package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.models.SearchResult;
import com.bandwidth.sdk.numbers.models.orders.Order;
import com.bandwidth.sdk.numbers.models.orders.OrderResponse;
import com.bandwidth.sdk.numbers.models.AvailableNumberSearchRequest;

public interface NumbersClient extends AutoCloseable {

   /**
    * Searches for available telephone number with the given search criteria.
    *
    * @param availableNumberSearchRequest {@link AvailableNumberSearchRequest} to specify the attributes of the desired
    *                                     phone numbers
    * @return {@link SearchResult} with the numbers the meet the given criteria. If enableTNDetail is specified the
    * {@link SearchResult} will populate the TelephoneNumberDetail list, otherwise only the telephone numbers will be
    * included in the telephone number list
    */
   SearchResult getAvailableTelephoneNumbers(AvailableNumberSearchRequest availableNumberSearchRequest);

   /**
    * Order telephone numbers with the given criteria. The {@link Order} is a wrapper around a specific order type.
    * Order must have a site id and a peer id (sip peer id) specified. The specific order type must specify a quantity.
    *
    * @param order {@link Order} wrapping the specific order type for the numbers desired.
    * @return {@link OrderResponse} with the details of the results of placing the order
    */
   OrderResponse orderTelephoneNumbers(Order order);

   /**
    * Fetch the details of an order with the given order id.
    *
    * @param orderId The id of the order to check the status of
    * @return {@link OrderResponse} with the details of the results of placing the order
    */
   OrderResponse getOrderStatus(String orderId);
}
