package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableOrdersResponseWrapper.class)
@JsonDeserialize(as = ImmutableOrdersResponseWrapper.class)
@JacksonXmlRootElement(localName = "ListOrderIdUserIdDate")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class OrdersResponseWrapper {
    @Nullable
    @JacksonXmlProperty(localName = "TotalCount")
    public abstract Integer getTotalCount();

    @Nullable
    @JacksonXmlElementWrapper(localName = "Links")
    public abstract OrdersResponseLinks getLinks();

    @Nullable
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "OrderIdUserIdDate")
    public abstract List<OrdersResponseData> getOrders();

    public static ImmutableOrdersResponseWrapper.Builder builder() {
        return ImmutableOrdersResponseWrapper.builder();
    }
}
