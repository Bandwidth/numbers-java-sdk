package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableNpaNxxSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableNpaNxxSearchAndOrderType.class)
@JacksonXmlRootElement(localName = "NpaNxxSearchAndOrderType")
public abstract class NpaNxxSearchAndOrderType implements Quantifiable {

   @JacksonXmlProperty(localName = "NpaNxx")
   public abstract String getNpaNxx();

   @Nullable
   @JacksonXmlProperty(localName = "EnableTNDetail")
   public abstract Boolean getEnableTNDetail();

   @Nullable
   @JacksonXmlProperty(localName = "EnableLCA")
   @Value.Default
   public Boolean getEnableLca() {
      return false;
   }

   public static ImmutableNpaNxxSearchAndOrderType.Builder builder() {
      return ImmutableNpaNxxSearchAndOrderType.builder();
   }

}
