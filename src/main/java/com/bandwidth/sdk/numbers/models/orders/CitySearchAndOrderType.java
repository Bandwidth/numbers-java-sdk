package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JacksonXmlRootElement(localName = "CitySearchAndOrderType")
@JsonSerialize(as = ImmutableCitySearchAndOrderType.class)
@JsonDeserialize(as = ImmutableCitySearchAndOrderType.class)
public abstract class CitySearchAndOrderType implements Quantifiable {

   @Nullable
   @JacksonXmlProperty(localName = "City")
   public abstract String getCity();

   public static ImmutableCitySearchAndOrderType.Builder builder() {
      return ImmutableCitySearchAndOrderType.builder();
   }

}
