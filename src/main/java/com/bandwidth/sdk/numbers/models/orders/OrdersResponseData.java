package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Date;

@Value.Immutable
@JsonSerialize(as = ImmutableOrdersResponseData.class)
@JsonDeserialize(as = ImmutableOrdersResponseData.class)
@JacksonXmlRootElement(localName = "OrderIdUserIdDate")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class OrdersResponseData {
    @Nullable
    @JacksonXmlProperty(localName = "accountId")
    public abstract String getAccountId();

    @Nullable
    @JacksonXmlProperty(localName = "CountOfTNs")
    public abstract Integer getCountOfTns();

    @Nullable
    @JacksonXmlProperty(localName = "CustomerOrderId")
    public abstract String getCustomerOrderId();

    @Nullable
    @JacksonXmlProperty(localName = "userId")
    public abstract String getUserId();

    @Nullable
    @JacksonXmlProperty(localName = "lastModifiedDate")
    public abstract Date getLastModifiedDate();

    @Nullable
    @JacksonXmlProperty(localName = "OrderDate")
    public abstract Date getOrderDate();

    @Nullable
    @JacksonXmlProperty(localName = "OrderType")
    public abstract String getOrderType();

    @Nullable
    @JacksonXmlProperty(localName = "orderId")
    public abstract String getOrderId();

    @Nullable
    @JacksonXmlProperty(localName = "OrderStatus")
    public abstract String getOrderStatus();

    @Nullable
    @JacksonXmlProperty(localName = "Summary")
    public abstract String getSummary();

    public static ImmutableOrdersResponseData.Builder builder() {
        return ImmutableOrdersResponseData.builder();
    }
}
