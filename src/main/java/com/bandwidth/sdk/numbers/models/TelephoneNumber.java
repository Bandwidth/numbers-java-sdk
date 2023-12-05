package com.bandwidth.sdk.numbers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Date;

@Value.Immutable
@JacksonXmlRootElement
@JsonSerialize(as = ImmutableTelephoneNumber.class)
@JsonDeserialize(as = ImmutableTelephoneNumber.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TelephoneNumber {

   @Nullable
   @JacksonXmlProperty(localName = "FullNumber")
   public abstract String getFullNumber();

   @Nullable
   @JacksonXmlProperty(localName = "City")
   public abstract String getCity();

   @Nullable
   @JacksonXmlProperty(localName = "Lata")
   public abstract String getLata();

   @Nullable
   @JacksonXmlProperty(localName = "State")
   public abstract String getState();

   @Nullable
   @JacksonXmlProperty(localName = "RateCenter")
   public abstract String getRateCenter();

   @Nullable
   @JacksonXmlProperty(localName = "Tier")
   public abstract String getTier();

   @Nullable
   @JacksonXmlProperty(localName = "VendorId")
   public abstract String getVendorId();

   @Nullable
   @JacksonXmlProperty(localName = "VendorlocalName")
   public abstract String getVendorlocalName();

   @Nullable
   @JacksonXmlProperty(localName = "Status")
   public abstract String getStatus();

   @Nullable
   @JacksonXmlProperty(localName = "AccountId")
   public abstract String getAccountId();

   @Nullable
   @JacksonXmlProperty(localName = "LastModifiedDate")
   public abstract Date getLastModifiedDate();
}
