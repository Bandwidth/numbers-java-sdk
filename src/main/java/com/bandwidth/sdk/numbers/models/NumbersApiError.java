package com.bandwidth.sdk.numbers.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableNumbersApiError.class)
@JsonDeserialize(as = ImmutableNumbersApiError.class)
public abstract class NumbersApiError {
    public abstract String getType();
    public abstract String getDescription();
    public abstract List<NumbersApiFieldError> getFieldErrors();
}
