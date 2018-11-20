package com.bandwidth.sdk.numbers.exception;

public class NumbersApiException extends RuntimeException {

   public NumbersApiException(String message) {
      super(message);
   }

   public NumbersApiException(Throwable cause) {
      super(cause);
   }

   public NumbersApiException(String message, Throwable cause) {
      super(message, cause);
   }

}
