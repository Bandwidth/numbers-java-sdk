package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableOrdersResponse.class)
@JsonDeserialize(as = ImmutableOrdersResponse.class)
@JacksonXmlRootElement(localName = "ResponseSelectWrapper")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class OrdersResponse {
    @Value.Redacted
    @Nullable
    @JacksonXmlProperty(localName = "ListOrderIdUserIdDate")
    public abstract OrdersResponseWrapper getOrdersResponseData();

    public static ImmutableOrdersResponse.Builder builder() {
        return ImmutableOrdersResponse.builder();
    }
}
