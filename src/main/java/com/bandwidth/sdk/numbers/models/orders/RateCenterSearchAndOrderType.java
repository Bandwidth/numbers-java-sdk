package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableRateCenterSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableRateCenterSearchAndOrderType.class)
@JacksonXmlRootElement(localName = "RateCenterSearchAndOrderType")
public abstract class RateCenterSearchAndOrderType implements Quantifiable {

   @JacksonXmlProperty(localName = "RateCenter")
   public abstract String getRateCenter();

   @JacksonXmlProperty(localName = "State")
   public abstract String getState();

   @Nullable
   @JacksonXmlProperty(localName = "EnableLCA")
   @Value.Default
   public Boolean getEnableLca() {
      return false;
   }

   public static ImmutableRateCenterSearchAndOrderType.Builder builder() {
      return ImmutableRateCenterSearchAndOrderType.builder();
   }

}
