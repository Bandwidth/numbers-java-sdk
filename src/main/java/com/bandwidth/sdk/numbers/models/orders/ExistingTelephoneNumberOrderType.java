package com.bandwidth.sdk.numbers.models.orders;

import com.bandwidth.sdk.numbers.models.Quantifiable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableExistingTelephoneNumberOrderType.class)
@JsonDeserialize(as = ImmutableExistingTelephoneNumberOrderType.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "ExistingTelephoneNumberOrderType")
public abstract class ExistingTelephoneNumberOrderType implements Quantifiable {

   @JacksonXmlElementWrapper(localName = "TelephoneNumberList")
   @JacksonXmlProperty(localName = "TelephoneNumber")
   public abstract List<String> getTelephoneNumberList();

   @JacksonXmlElementWrapper(localName = "ReservationIdList")
   @JacksonXmlProperty(localName = "ReservationId")
   public abstract List<String> getReservationIdList();

   public static ImmutableExistingTelephoneNumberOrderType.Builder builder() {
      return ImmutableExistingTelephoneNumberOrderType.builder();
   }
}
