package com.bandwidth.sdk.numbers.exception;

import com.bandwidth.sdk.numbers.serde.NumbersSerde;
import com.bandwidth.sdk.numbers.models.NumbersApiError;
import org.asynchttpclient.Response;

public class NumbersServiceException extends RuntimeException {

    private final NumbersApiError error;

    public NumbersServiceException(NumbersApiError error) {
        super(error.toString());
        this.error = error;
    }

    public NumbersApiError getError() {
        return error;
    }

    public static void throwIfApiError(Response apiResponse) {
        if (!isSuccessfulHttpStatusCode(apiResponse.getStatusCode())) {
            try {
                throw new NumbersServiceException(
                   NumbersSerde.deserialize(apiResponse.getResponseBody(), NumbersApiError.class)
                );
            } catch (Exception e) {
                throw new NumbersClientException("Unknown error response from API: " + apiResponse);
            }
        }
    }

    static boolean isSuccessfulHttpStatusCode(int statusCode) {
        return (statusCode / 100) == 2;
    }
}
