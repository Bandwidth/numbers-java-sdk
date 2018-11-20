package com.bandwidth.sdk.numbers.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

@Value.Immutable
@JacksonXmlRootElement(localName = "TelephoneNumberDetail")
@JsonSerialize(as = ImmutableTelephoneNumberDetail.class)
@JsonDeserialize(as = ImmutableTelephoneNumberDetail.class)
public abstract class TelephoneNumberDetail {

   @JacksonXmlProperty(localName = "FullNumber")
   public abstract String getFullNumber();
   @JacksonXmlProperty(localName = "City")
   public abstract String getCity();
   @JacksonXmlProperty(localName = "LATA")
   public abstract String getLata();
   @JacksonXmlProperty(localName = "State")
   public abstract String getState();
   @JacksonXmlProperty(localName = "RateCenter")
   public abstract String getRateCenter();
   @JacksonXmlProperty(localName = "Tier")
   public abstract String getTier();
   @JacksonXmlProperty(localName = "VendorId")
   public abstract String getVendorId();
   @JacksonXmlProperty(localName = "VendorName")
   public abstract String getVendorLocalName();
}
