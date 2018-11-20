package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableTollFreeVanitySearchAndOrderType.class)
@JsonDeserialize(as = ImmutableTollFreeVanitySearchAndOrderType.class)
@JacksonXmlRootElement(localName = "TollFreeVanitySearchAndOrderType")
public abstract class TollFreeVanitySearchAndOrderType {

   @JacksonXmlProperty(localName = "TollFreeVanity")
   public abstract String getTollFreeVanity();

   @Nullable
   @Value.Default
   @JacksonXmlProperty(localName = "Quantity")
   public Integer getQuantity() {
      return 1;
   }

   public static ImmutableTollFreeVanitySearchAndOrderType.Builder builder() {
      return ImmutableTollFreeVanitySearchAndOrderType.builder();
   }

}
