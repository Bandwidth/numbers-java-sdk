package com.bandwidth.sdk.numbers.exception;

public interface ThrowableSupplier<T> {
    T get() throws Throwable;
}
