package com.bandwidth.sdk.numbers;

@FunctionalInterface
public interface BackoffFunction<T> {
   T execute();
}