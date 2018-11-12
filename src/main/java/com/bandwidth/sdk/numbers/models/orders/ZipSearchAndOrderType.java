package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JacksonXmlRootElement(localName = "ZipSearchAndOrderType")
@JsonSerialize(as = ImmutableZipSearchAndOrderType.class)
@JsonDeserialize(as = ImmutableZipSearchAndOrderType.class)
public abstract class ZipSearchAndOrderType implements Quantifiable {

   @Nullable
   @JacksonXmlProperty(localName = "Zip")
   public abstract String getZip();

   public static ImmutableZipSearchAndOrderType.Builder builder() {
      return ImmutableZipSearchAndOrderType.builder();
   }
}
