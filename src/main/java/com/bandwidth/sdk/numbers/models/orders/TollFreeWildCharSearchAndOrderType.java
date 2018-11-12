package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

@Value.Immutable
@JacksonXmlRootElement(localName = "TollFreeWildCharSearchAndOrderType")
@JsonSerialize(as = ImmutableTollFreeWildCharSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableTollFreeWildCharSearchAndOrderType.class)
public abstract class TollFreeWildCharSearchAndOrderType implements Quantifiable {

   @JacksonXmlProperty(localName = "TollFreeWildCharPattern")
   public abstract String getTollFreeWildCharPattern();

   public static ImmutableTollFreeWildCharSearchAndOrderType.Builder builder() {
      return ImmutableTollFreeWildCharSearchAndOrderType.builder();
   }

}
