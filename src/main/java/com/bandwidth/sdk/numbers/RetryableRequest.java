package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.exception.NumbersClientException;

import java.util.function.Predicate;

public class RetryableRequest {

   private static final long[] BACKOFF_SEQUENCE =
      new long[] { 100, 100, 200, 300, 500, 800, 1300, 2100, 3400, 5500, 8900, 14400 };

   private RetryableRequest() {
      // utility class, no instances
   }

   public static <T> T execute(BackoffFunction<T> fn, Predicate<T> isResultTerminal) {
      for (int attempt = 0; attempt < BACKOFF_SEQUENCE.length; attempt++) {
         final T result = fn.execute();
         if (isResultTerminal.test(result)) {
            return result;
         } else {
            backoff(attempt);
         }
      }

      throw new NumbersClientException("Request failed!");
   }

   private static void backoff(int attempt) {
      try {
         Thread.sleep(BACKOFF_SEQUENCE[attempt]);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         throw new NumbersClientException(e);
      }
   }
}