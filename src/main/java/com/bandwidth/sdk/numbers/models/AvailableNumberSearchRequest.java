package com.bandwidth.sdk.numbers.models;

import com.bandwidth.sdk.numbers.serde.NumbersSerde;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.asynchttpclient.Param;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Value.Immutable
@JsonSerialize(as = ImmutableAvailableNumberSearchRequest.class)
@JsonDeserialize(as = ImmutableAvailableNumberSearchRequest.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AvailableNumberSearchRequest {

   @Nullable
   public abstract String getAreaCode();

   @Nullable
   public abstract String getNpaNxx();

   @Nullable
   public abstract String getNpaNxxx();

   @Nullable
   public abstract String getRateCenter();

   @Nullable
   public abstract String getCity();

   @Nullable
   public abstract String getState();

   @Nullable
   public abstract String getZip();

   @Nullable
   public abstract String getLata();

   @Nullable
   public abstract String getLocalVanity();

   @Nullable
   public abstract String getTollFreeVanity();

   @Nullable
   public abstract String getTollFreeWildCardPattern();

   @Nullable
   public abstract Boolean getEnableTNDetail();

   @Nullable
   public abstract String getLCA();

   @Nullable
   public abstract String getEndsIn();

   @Nullable
   public abstract OrderBy getOrderBy();

   @Nullable
   public abstract Integer getQuantity();

   public static ImmutableAvailableNumberSearchRequest.Builder builder() {
      return ImmutableAvailableNumberSearchRequest.builder();
   }

   public List<Param> toParams() {
      HashMap<String, String> map = NumbersSerde.MAPPER.convertValue(
         this,
         new TypeReference<HashMap<String, String>>() {
         });

      return map.entrySet()
         .stream()
         .map(entry -> new Param(entry.getKey(), entry.getValue()))
         .collect(Collectors.toList());
   }

   public enum OrderBy {
      @JsonProperty("fullNumber")
      FULL_NUMBER,
      @JsonProperty("npaNxx")
      NPA_NXX,
      @JsonProperty("npaNxxx")
      NPA_NXXX,
      @JsonProperty("areaCode")
      AREA_CODE
   }

}
