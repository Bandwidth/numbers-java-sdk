package com.bandwidth.sdk.numbers.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JacksonXmlRootElement
@JsonSerialize(as = ImmutableSearchResult.class)
@JsonDeserialize(as = ImmutableSearchResult.class)
public abstract class SearchResult {

   @JacksonXmlProperty(localName = "Error")
   @Nullable
   public abstract com.bandwidth.sdk.numbers.models.Error getError();

   @JacksonXmlProperty(localName = "ResultCount")
   @Nullable
   public abstract Integer getResultCount();

   @JacksonXmlElementWrapper(localName = "TelephoneNumberList")
   @JacksonXmlProperty(localName = "TelephoneNumber")
   public abstract List<String> getTelephoneNumberList();

   @JacksonXmlElementWrapper(localName = "TelephoneNumberDetailList")
   @JacksonXmlProperty(localName = "TelephoneNumberDetail")
   public abstract List<TelephoneNumberDetail> getTelephoneNumberDetailList();
}
