package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableStateSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableStateSearchAndOrderType.class)
@JacksonXmlRootElement(localName = "StateSearchAndOrderType")
public abstract class StateSearchAndOrderType {

   @JacksonXmlProperty(localName = "State")
   public abstract String getState();

   @Nullable
   @Value.Default
   @JacksonXmlProperty(localName = "Quantity")
   public Integer getQuantity() {
      return 1;
   }

   public static ImmutableStateSearchAndOrderType.Builder builder() {
      return ImmutableStateSearchAndOrderType.builder();
   }

}
