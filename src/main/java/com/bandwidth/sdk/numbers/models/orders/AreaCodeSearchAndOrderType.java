package com.bandwidth.sdk.numbers.models.orders;


import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableAreaCodeSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableAreaCodeSearchAndOrderType.class)
@JacksonXmlRootElement(localName = "AreaCodeSearchAndOrderType")
public abstract class AreaCodeSearchAndOrderType implements Quantifiable {

   @Nullable
   @JacksonXmlProperty(localName = "AreaCode")
   public abstract String getAreaCode();

   public static ImmutableAreaCodeSearchAndOrderType.Builder builder() {
      return ImmutableAreaCodeSearchAndOrderType.builder();
   }

}
