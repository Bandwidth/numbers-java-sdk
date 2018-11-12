package com.bandwidth.sdk.numbers.exception;


public class NumbersClientException extends RuntimeException {

    public NumbersClientException(String message) {
        super(message);
    }

    public NumbersClientException(Throwable cause) {
        super(cause);
    }

    public NumbersClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
