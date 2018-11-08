package com.bandwidth.sdk.numbers.exception;

import java.util.concurrent.CompletableFuture;

public class ExceptionUtils {

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

}
