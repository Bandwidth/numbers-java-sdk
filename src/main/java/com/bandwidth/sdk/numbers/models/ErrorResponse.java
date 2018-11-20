package com.bandwidth.sdk.numbers.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

@Value.Immutable
@JacksonXmlRootElement(localName = "Error")
@JsonSerialize(as = ImmutableErrorResponse.class)
@JsonDeserialize(as = ImmutableErrorResponse.class)
public abstract class ErrorResponse {

   private static final String ORDER_PENDING_ERROR_CODE = "5019";

   @JacksonXmlProperty(localName = "Code")
   public abstract String getCode();
   @JacksonXmlProperty(localName = "Description")
   public abstract String getDescription();

   public boolean isOrderPendingError() {
      return getCode().equals(ORDER_PENDING_ERROR_CODE);
   }

}
