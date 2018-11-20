package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableLataSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableLataSearchAndOrderType.class)
@JacksonXmlRootElement(localName = "LataSearchAndOrderType")
public abstract class LataSearchAndOrderType {

   @Nullable
   @JacksonXmlProperty(localName = "LATA")
   public abstract String getLata();

   @Nullable
   @Value.Default
   @JacksonXmlProperty(localName = "Quantity")
   public Integer getQuantity() {
      return 1;
   }

   public static ImmutableLataSearchAndOrderType.Builder builder() {
      return ImmutableLataSearchAndOrderType.builder();
   }

}
