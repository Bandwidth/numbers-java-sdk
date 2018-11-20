package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableTollFreeWildCharSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableTollFreeWildCharSearchAndOrderType.class)
@JacksonXmlRootElement(localName = "TollFreeWildCharSearchAndOrderType")
public abstract class TollFreeWildCharSearchAndOrderType {

   @JacksonXmlProperty(localName = "TollFreeWildCardPattern")
   public abstract String getTollFreeWildCardPattern();

   @Nullable
   @Value.Default
   @JacksonXmlProperty(localName = "Quantity")
   public Integer getQuantity() {
      return 1;
   }

   public static ImmutableTollFreeWildCharSearchAndOrderType.Builder builder() {
      return ImmutableTollFreeWildCharSearchAndOrderType.builder();
   }

}
