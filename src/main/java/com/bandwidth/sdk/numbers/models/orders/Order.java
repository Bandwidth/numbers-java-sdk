package com.bandwidth.sdk.numbers.models.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Date;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(as = ImmutableOrder.class)
@JsonDeserialize(as = ImmutableOrder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "Order")
public abstract class Order {

   @Nullable
   @Value.Redacted
   @JacksonXmlProperty(localName = "CustomerOrderId")
   public abstract String getCustomerOrderId();

   @JacksonXmlProperty(localName = "SiteId")
   public abstract String getSiteId();

   @Nullable
   @JacksonXmlProperty(localName = "PeerId")
   public abstract String getPeerId();

   @Nullable
   @Value.Redacted
   @JacksonXmlProperty(localName = "PartialAllowed")
   public Boolean getPartialAllowed() {
      return false;
   }

   @Nullable
   @JacksonXmlProperty(localName = "OrderCreateDate")
   public abstract Date getOrderCreateDate();

   @Nullable
   @JacksonXmlProperty(localName = "id")
   public abstract String getId();

   @Nullable
   @JacksonXmlProperty(localName = "ExistingTelephoneNumberOrderType")
   public abstract ExistingTelephoneNumberOrderType getExistingTelephoneNumberOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "AreaCodeSearchAndOrderType")
   public abstract AreaCodeSearchAndOrderType getAreaCodeSearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "CitySearchAndOrderType")
   public abstract CitySearchAndOrderType getCitySearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "LATASearchAndOrderType")
   public abstract LataSearchAndOrderType lataSearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "NPANXXSearchAndOrderType")
   public abstract NpaNxxSearchAndOrderType npaNxxSearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "RateCenterSearchAndOrderType")
   public abstract RateCenterSearchAndOrderType rateCenterSearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "StateSearchAndOrderType")
   public abstract StateSearchAndOrderType stateSearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "TollFreeVanitySearchAndOrderType")
   public abstract TollFreeVanitySearchAndOrderType getTollFreeVanitySearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "TollFreeWildCharSearchAndOrderType")
   public abstract TollFreeWildCharSearchAndOrderType getTollFreeWildCharSearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "ZIPSearchAndOrderType")
   public abstract ZipSearchAndOrderType getZipSearchAndOrderType();

   @Nullable
   @JacksonXmlProperty(localName = "CombinedSearchAndOrderType")
   public abstract CombinedSearchAndOrderType getCombinedSearchAndOrderType();

   public static ImmutableOrder.Builder builder() {
      return ImmutableOrder.builder();
   }


}
