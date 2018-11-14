package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCombinedSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableCombinedSearchAndOrderType.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "CombinedSearchAndOrderType")
public abstract class CombinedSearchAndOrderType implements Quantifiable {

   @Nullable
   @JacksonXmlProperty(localName = "AreaCode")
   public abstract String getNpa();

   @Nullable
   @JacksonXmlProperty(localName = "NpaNxx")
   public abstract String getNpaNxx();

   @Nullable
   @JacksonXmlProperty(localName = "NpaNxxX")
   public abstract String getNpaNxxBlock();

   @Nullable
   @JacksonXmlProperty(localName = "RateCenter")
   public abstract String getRateCenterAbbreviation();

   @Nullable
   @JacksonXmlProperty(localName = "LATA")
   public abstract String getLata();

   @Nullable
   @JacksonXmlProperty(localName = "City")
   public abstract String getCity();

   @Nullable
   @JacksonXmlProperty(localName = "State")
   public abstract String getState();

   @Nullable
   @JacksonXmlProperty(localName = "Zip")
   public abstract String getZip();

   @Nullable
   @JacksonXmlProperty(localName = "LocalVanity")
   public abstract String getLocalVanity();

   @Nullable
   @JacksonXmlProperty(localName = "EndsIn")
   public abstract Boolean getEndsIn();

   @Nullable
   @JacksonXmlProperty(localName = "EnableLCA")
   public abstract Boolean getEnableLca();

   public static ImmutableCombinedSearchAndOrderType.Builder builder() {
      return ImmutableCombinedSearchAndOrderType.builder();
   }

}
