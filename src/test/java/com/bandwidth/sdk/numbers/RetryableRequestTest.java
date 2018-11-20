package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.helpers.RetryPolicy;
import com.bandwidth.sdk.numbers.helpers.RetryableRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class RetryableRequestTest {

   private static final RetryPolicy THROWING_RETRY_POLICY = retryAttempt -> {
      throw new RuntimeException();
   };
   private static final RetryPolicy NOOP_RETRY_POLICY = retryAttempt -> {
   };

   @Test
   public void shouldThrowExceptionWhenRetryPolicyThrows() {
      Throwable thrown = Assertions.catchThrowable(() ->
         RetryableRequest.executeRequest(() -> null, (Predicate<Void>) v -> false, THROWING_RETRY_POLICY));

      assertThat(thrown).isInstanceOf(RuntimeException.class);
   }

   @Test
   public void shouldRetryOnceThenSucceed() {

      final boolean[] results = new boolean[]{false, true};
      AtomicInteger atomicInteger = new AtomicInteger(0);

      RetryableRequest.executeRequest(() -> results[atomicInteger.getAndIncrement()], Boolean::booleanValue, NOOP_RETRY_POLICY);
      assertEquals(2, atomicInteger.get());
   }

}