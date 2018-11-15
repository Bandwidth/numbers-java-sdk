package com.bandwidth.sdk.numbers.exception;

import com.bandwidth.sdk.numbers.models.ErrorResponse;
import com.google.common.base.Joiner;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ExceptionUtils {

   private ExceptionUtils() {
      // utility class; no instances
   }

   public static <T> CompletableFuture<T> catchAsyncClientExceptions(ThrowableSupplier<CompletableFuture<T>> supplier) {
      try {
         return supplier.get();
      } catch (Throwable e) {
         CompletableFuture<T> future = new CompletableFuture<>();
         future.completeExceptionally(new NumbersClientException(e));
         return future;
      }
   }

   public static <T> T catchClientExceptions(ThrowableSupplier<T> supplier) {
      try {
         return supplier.get();
      } catch (Throwable e) {
         throw new NumbersClientException(e);
      }
   }

   public static NumbersApiException consolidateApiErrors(List<ErrorResponse> errorList) {
      return new NumbersApiException(Joiner.on(",").join(errorList));
   }
}
