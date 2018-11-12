package com.bandwidth.sdk.numbers.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.annotation.Nullable;

public interface Quantifiable {

   @Nullable
   @JacksonXmlProperty(localName = "Quantity")
   Integer getQuantity();

}
