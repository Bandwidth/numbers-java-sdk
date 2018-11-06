package com.bandwidth.sdk;
  

public class NumbersException extends RuntimeException {


    public NumbersException(String message) {
        super(message);
    }

    public NumbersException(Throwable cause) {
        super(cause);
    }

    public NumbersException(String message, Throwable cause) {
        super(message, cause);
    }
}
