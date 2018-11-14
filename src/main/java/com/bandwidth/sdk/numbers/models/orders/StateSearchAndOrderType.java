package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableStateSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableStateSearchAndOrderType.class)
@JacksonXmlRootElement(localName = "StateSearchAndOrderType")
public abstract class StateSearchAndOrderType implements Quantifiable {

   @JacksonXmlProperty(localName = "State")
   public abstract String getState();

   public static ImmutableStateSearchAndOrderType.Builder builder() {
      return ImmutableStateSearchAndOrderType.builder();
   }

}
