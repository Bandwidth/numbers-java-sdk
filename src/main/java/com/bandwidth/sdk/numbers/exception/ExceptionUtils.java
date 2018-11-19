package com.bandwidth.sdk.numbers.exception;

import com.bandwidth.sdk.numbers.models.ErrorResponse;
import com.bandwidth.sdk.numbers.models.orders.OrderResponse;
import com.google.common.base.Joiner;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

   public static OrderResponse validateOrderResponse(Supplier<OrderResponse> orderResponseSupplier) {

      OrderResponse orderResponse = orderResponseSupplier.get();

      List<ErrorResponse> errorList = orderResponse.getErrorList();
      if (errorList == null || errorList.size() == 1 && errorList.get(0).isOrderPendingError()) {
         return orderResponse;
      }

      throw new NumbersApiException(Joiner.on(',').join(errorList.stream()
         .filter(errorResponse -> !errorResponse.isOrderPendingError())
         .collect(Collectors.toList())));
   }
}
