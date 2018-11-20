package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableZipSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableZipSearchAndOrderType.class)
@JacksonXmlRootElement(localName = "ZipSearchAndOrderType")
public abstract class ZipSearchAndOrderType {

   @Nullable
   @JacksonXmlProperty(localName = "Zip")
   public abstract String getZip();

   @Nullable
   @Value.Default
   @JacksonXmlProperty(localName = "Quantity")
   public Integer getQuantity() {
      return 1;
   }

   public static ImmutableZipSearchAndOrderType.Builder builder() {
      return ImmutableZipSearchAndOrderType.builder();
   }
}
