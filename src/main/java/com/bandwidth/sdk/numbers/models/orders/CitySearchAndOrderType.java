package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCitySearchAndOrderType.class)
@JsonDeserialize(as = ImmutableCitySearchAndOrderType.class)
@JacksonXmlRootElement(localName = "CitySearchAndOrderType")
public abstract class CitySearchAndOrderType {

   @Nullable
   @JacksonXmlProperty(localName = "City")
   public abstract String getCity();

   @Nullable
   @JacksonXmlProperty(localName = "State")
   public abstract String getState();

   @Nullable
   @Value.Default
   @JacksonXmlProperty(localName = "Quantity")
   public Integer getQuantity() {
      return 1;
   }

   public static ImmutableCitySearchAndOrderType.Builder builder() {
      return ImmutableCitySearchAndOrderType.builder();
   }

}
