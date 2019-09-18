package com.bandwidth.sdk.numbers.serde;

import com.bandwidth.sdk.numbers.models.AvailableNumberSearchRequest;
import com.bandwidth.sdk.numbers.models.orders.CombinedSearchAndOrderType;
import com.bandwidth.sdk.numbers.models.orders.Order;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumbersSerdeTest {

   private AvailableNumberSearchRequest availableNumberSearchRequest = AvailableNumberSearchRequest.builder()
      .city("RALEIGH")
      .state("NC")
      .tollFreeWildCardPattern("8**")
      .quantity(5)
      .enableTNDetail(true)
      .build();

   private Order order = Order.builder()
      .siteId("1")
      .peerId("2")
      .combinedSearchAndOrderType(
         CombinedSearchAndOrderType.builder()
         .city("RALEIGH")
         .state("NC")
         .quantity(5)
         .build()
      ).build();

   @Test
   public void serializationDeserialization() {
      String serializedSearch = NumbersSerde.serialize(availableNumberSearchRequest);
      AvailableNumberSearchRequest deserializedSearch = NumbersSerde.deserialize(serializedSearch, AvailableNumberSearchRequest.class);
      assertThat(deserializedSearch).isEqualTo(availableNumberSearchRequest);

      String serializedOrder = NumbersSerde.serialize(order);
      Order deserializedOrder = NumbersSerde.deserialize(serializedOrder, Order.class);
      assertThat(deserializedOrder).isEqualTo(order);
   }

}
