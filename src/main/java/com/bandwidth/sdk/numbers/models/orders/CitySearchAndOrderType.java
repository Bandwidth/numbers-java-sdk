package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
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
public abstract class CitySearchAndOrderType implements Quantifiable {

   @Nullable
   @JacksonXmlProperty(localName = "City")
   public abstract String getCity();

   @Nullable
   @JacksonXmlProperty(localName = "State")
   public abstract String getState();

   public static ImmutableCitySearchAndOrderType.Builder builder() {
      return ImmutableCitySearchAndOrderType.builder();
   }

}
