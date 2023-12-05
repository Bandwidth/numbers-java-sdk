package com.bandwidth.sdk.numbers.models;

import com.bandwidth.sdk.numbers.models.orders.OrderResponse;
import com.bandwidth.sdk.numbers.serde.NumbersSerde;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderResponseTest {
    private static final String GET_ORDER_RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <OrderResponse> <CompletedQuantity>1</CompletedQuantity> <CreatedByUser>test-user</CreatedByUser> <LastModifiedDate>2023-12-04T23:22:20.895Z</LastModifiedDate> <OrderCompleteDate>2023-12-04T23:22:20.892Z</OrderCompleteDate> <Order> <CustomerOrderId>MyTestOrderId</CustomerOrderId> <OrderCreateDate>2023-12-04T23:22:14.978Z</OrderCreateDate> <PeerId>616387</PeerId> <BackOrderRequested>false</BackOrderRequested> <AreaCodeSearchAndOrderType> <AreaCode>919</AreaCode> <Quantity>1</Quantity> </AreaCodeSearchAndOrderType> <PartialAllowed>true</PartialAllowed> <SiteId>34692</SiteId> <TnAttributes/> </Order> <OrderStatus>COMPLETE</OrderStatus> <CompletedNumbers> <TelephoneNumber> <CountryCodeA3>USA</CountryCodeA3> <FullNumber>9192052533</FullNumber> </TelephoneNumber> </CompletedNumbers> <Summary>1 number ordered in (919)</Summary> <FailedQuantity>0</FailedQuantity> </OrderResponse>";

    @Test
    public void deserializeOrder() {
        OrderResponse order = NumbersSerde.deserialize(GET_ORDER_RESPONSE, OrderResponse.class);
    }
}
