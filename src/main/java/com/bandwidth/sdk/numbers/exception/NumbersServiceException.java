package com.bandwidth.sdk.numbers.exception;

import com.bandwidth.sdk.numbers.models.NumbersApiError;
import org.asynchttpclient.Response;

public class NumbersServiceException extends RuntimeException {

    private NumbersApiError error;

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
                        //TODO: After NumbersSerde() is created, change this to something like this:
                            //new NumbersSerde().deserialize(apiResponse.getResponseBody(), NumbersApiError.class)
                        new NumbersApiError()
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
