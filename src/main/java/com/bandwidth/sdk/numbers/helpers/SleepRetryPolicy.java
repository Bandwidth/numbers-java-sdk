package com.bandwidth.sdk.numbers.helpers;

import com.bandwidth.sdk.numbers.exception.NumbersClientException;

public class SleepRetryPolicy implements RetryPolicy {

   private static final long[] BACKOFF_SEQUENCE =
      new long[] { 100, 100, 200, 300, 500, 800, 1300, 2100, 3400, 5500, 8900, 14400 };

   @Override
   public void doRetry(int retryAttempt) {
      if (retryAttempt >= BACKOFF_SEQUENCE.length) {
         throw new NumbersClientException("Request timed out.");
      }

      try {
         Thread.sleep(BACKOFF_SEQUENCE[retryAttempt]);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         throw new NumbersClientException(e);
      }
   }
}
