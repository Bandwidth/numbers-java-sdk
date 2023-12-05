package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.ErrorResponse;
import com.bandwidth.sdk.numbers.models.TelephoneNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIgnoreProperties(ignoreUnknown = true)
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
   public abstract Integer getPendingQuantity();

   @Nullable
   @JacksonXmlProperty(localName = "OrderCompleteDate")
   public abstract Date getOrderCompleteDate();

   @Nullable
   @JacksonXmlProperty(localName = "LastModifiedDate")
   public abstract Date getLastModifiedDate();

   @Nullable
   @JacksonXmlProperty(localName = "OrderStatus")
   public abstract OrderStatus getOrderStatus();

   @JacksonXmlElementWrapper(localName = "CompletedNumbers")
   @JacksonXmlProperty(localName = "TelephoneNumber")
   public abstract List<TelephoneNumber> getTelephoneNumbers();

   @Nullable
   @JacksonXmlElementWrapper(localName = "ErrorList")
   @JacksonXmlProperty(localName = "ErrorResponse")
   @Value.Redacted
   public abstract List<ErrorResponse> getErrorList();

   @JacksonXmlElementWrapper(localName = "FailedNumbers")
   @JacksonXmlProperty(localName = "FullNumber")
   public abstract List<String> getFailedNumbers();

   @Nullable
   @JacksonXmlProperty(localName = "Summary")
   public abstract String getSummary();

   public enum OrderStatus {
      COMPLETE(true),
      PARTIAL(true),
      FAILED(true),
      RECEIVED(false);

      private boolean isTerminal;

      OrderStatus(boolean isTerminal) {
         this.isTerminal = isTerminal;
      }

      public boolean isTerminal() {
         return isTerminal;
      }
   }

   @JsonIgnore
   public boolean isTerminal() {
      OrderStatus orderStatus = getOrderStatus();
      return orderStatus != null && orderStatus.isTerminal();
   }

}
