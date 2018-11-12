package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.TelephoneNumber;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableOrderResponse.class)
@JsonDeserialize(as = ImmutableOrderResponse.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "OrderResponse")
public abstract class OrderResponse {
   
   @JacksonXmlProperty(localName = "Order")
   public abstract Order getOrder();

   @Nullable
   @JacksonXmlProperty(localName = "CompletedQuantity")
   public abstract Integer getCompletedQuantity();

   @Nullable
   @JacksonXmlProperty(localName = "FailedQuantity")
   public abstract Integer getFailedQuantity();

   @Nullable
   @JacksonXmlProperty(localName = "CreatedByUser")
   public abstract String getCreatedByUser();

   @Nullable
   @JacksonXmlProperty(localName = "PendingQuantity")
   public abstract Integer pendingQuantity();

   @Nullable
   @JacksonXmlProperty(localName = "OrderCompletedDate")
   public abstract Date orderCompletedDate();

   @Nullable
   @JacksonXmlProperty(localName = "LastModifiedDate")
   public abstract Date lastModifiedDate();

   @JacksonXmlProperty(localName = "OrderStatus")
   public abstract String orderStatus();

   @JacksonXmlElementWrapper(localName = "CompletedNumbers")
   @JacksonXmlProperty(localName = "TelephoneNumber")
   public abstract List<TelephoneNumber> getTelephoneNumbers();
}
