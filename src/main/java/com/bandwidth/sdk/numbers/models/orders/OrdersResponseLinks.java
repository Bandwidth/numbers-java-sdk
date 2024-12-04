package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableOrdersResponseLinks.class)
@JsonDeserialize(as = ImmutableOrdersResponseLinks.class)
@JacksonXmlRootElement(localName = "Links")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class OrdersResponseLinks {
    @Nullable
    @JacksonXmlProperty(localName = "first")
    public abstract String getFirst();

    @Nullable
    @JacksonXmlProperty(localName = "next")
    public abstract String getNext();

    public static ImmutableOrdersResponseLinks.Builder builder() {
        return ImmutableOrdersResponseLinks.builder();
    }
}
