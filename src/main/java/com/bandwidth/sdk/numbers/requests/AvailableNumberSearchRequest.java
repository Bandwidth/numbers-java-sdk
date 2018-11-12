package com.bandwidth.sdk.numbers.requests;

public class AvailableNumberSearchRequest extends Request {

   public void quantity(int quantity) {
      addQueryParam("quantity", quantity);
   }

   public void enableTnDetail(boolean enableTnDetail) {
      addQueryParam("enableTNDetail", enableTnDetail);
   }

   public void state(String state) {
      addQueryParam("state", state);
   }

   public void city(String city) {
      addQueryParam("city", city);
   }
}
