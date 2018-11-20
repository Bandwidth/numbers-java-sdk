package com.bandwidth.sdk.numbers.helpers;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class RetryableRequest {

   private RetryableRequest() {
      // utility class, no instances
   }

   public static <T> T executeRequest(Supplier<T> fn, Predicate<T> resultTester, RetryPolicy retryPolicy) {
      T result = fn.get();
      int attempt = 0;

      while (!resultTester.test(result)) {
         retryPolicy.doRetry(attempt++);
         result = fn.get();
      }

      return result;
   }
}