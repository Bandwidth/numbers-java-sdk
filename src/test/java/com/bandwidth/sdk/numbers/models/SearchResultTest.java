package com.bandwidth.sdk.numbers.models;

import com.bandwidth.sdk.numbers.serde.NumbersSerde;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultTest {

   private static final String SEARCH_RESULT_TN_DETAIL_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><SearchResult><ResultCount>10</ResultCount><TelephoneNumberDetailList><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192972578</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192972582</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192972593</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192972630</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192972634</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192972652</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192972680</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192972737</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192978795</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail><TelephoneNumberDetail><City>CARY</City><LATA>426</LATA><RateCenter>CARY</RateCenter><State>NC</State><FullNumber>9192978903</FullNumber><Tier>0</Tier><VendorId>49</VendorId><VendorName>Bandwidth CLEC</VendorName></TelephoneNumberDetail></TelephoneNumberDetailList></SearchResult>";
   private static final String SEARCH_RESULT_NO_DETAIL_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><SearchResult><ResultCount>10</ResultCount><TelephoneNumberList><TelephoneNumber>9192972578</TelephoneNumber><TelephoneNumber>9192972582</TelephoneNumber><TelephoneNumber>9192972593</TelephoneNumber><TelephoneNumber>9192972630</TelephoneNumber><TelephoneNumber>9192972634</TelephoneNumber><TelephoneNumber>9192972652</TelephoneNumber><TelephoneNumber>9192972680</TelephoneNumber><TelephoneNumber>9192972737</TelephoneNumber><TelephoneNumber>9192978795</TelephoneNumber><TelephoneNumber>9192978903</TelephoneNumber></TelephoneNumberList></SearchResult>";
   private static List<String> TNS = ImmutableList.of(
      "9192972578",
      "9192972582",
      "9192972593",
      "9192972630",
      "9192972634",
      "9192972652",
      "9192972680",
      "9192972737",
      "9192978795",
      "9192978903");

   @Test
   public void extractTnsFromDetailResult() {
      SearchResult searchResult = NumbersSerde.deserialize(SEARCH_RESULT_TN_DETAIL_XML, SearchResult.class);
      assertThat(searchResult.extractTelephoneNumbers()).isEqualTo(TNS);
   }

   @Test
   public void extractTnsFromResult() {
      SearchResult searchResult = NumbersSerde.deserialize(SEARCH_RESULT_NO_DETAIL_XML, SearchResult.class);
      assertThat(searchResult.extractTelephoneNumbers()).isEqualTo(TNS);
   }

}