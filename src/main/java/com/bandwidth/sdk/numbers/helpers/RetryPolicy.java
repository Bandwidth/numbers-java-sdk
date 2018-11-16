package com.bandwidth.sdk.numbers.helpers;

@FunctionalInterface
public interface RetryPolicy {

   void doRetry(int retryAttempt);
}
