package com.bandwidth.sdk.numbers.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
@JacksonXmlRootElement
@JsonSerialize(as = ImmutableTelephoneNumber.class)
@JsonDeserialize(as = ImmutableTelephoneNumber.class)
public abstract class TelephoneNumber {

   @JacksonXmlProperty(localName = "FullNumber")
   public abstract String getFullNumber();
   @JacksonXmlProperty(localName = "City")
   public abstract String getCity();
   @JacksonXmlProperty(localName = "Lata")
   public abstract String getLata();
   @JacksonXmlProperty(localName = "State")
   public abstract String getState();
   @JacksonXmlProperty(localName = "RateCenter")
   public abstract String getRateCenter();
   @JacksonXmlProperty(localName = "Tier")
   public abstract String getTier();
   @JacksonXmlProperty(localName = "VendorId")
   public abstract String getVendorId();
   @JacksonXmlProperty(localName = "VendorlocalName")
   public abstract String getVendorlocalName();
   @JacksonXmlProperty(localName = "Status")
   public abstract String getStatus();
   @JacksonXmlProperty(localName = "AccountId")
   public abstract String getAccountId();
   @JacksonXmlProperty(localName = "LastModifiedDate")
   public abstract Date getLastModifiedDate();
}


