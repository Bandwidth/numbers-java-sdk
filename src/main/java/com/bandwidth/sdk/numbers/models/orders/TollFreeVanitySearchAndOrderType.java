package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTollFreeVanitySearchAndOrderType.class)
@JsonDeserialize(as = ImmutableTollFreeVanitySearchAndOrderType.class)
@JacksonXmlRootElement(localName = "TollFreeVanitySearchAndOrderType")
public abstract class TollFreeVanitySearchAndOrderType implements Quantifiable {

   @JacksonXmlProperty(localName = "TollFreeVanity")
   public abstract String getTollFreeVanity();

   public static ImmutableTollFreeVanitySearchAndOrderType.Builder builder() {
      return ImmutableTollFreeVanitySearchAndOrderType.builder();
   }

}
