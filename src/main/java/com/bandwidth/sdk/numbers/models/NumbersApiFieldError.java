package com.bandwidth.sdk.numbers.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableNumbersApiFieldError.class)
@JsonDeserialize(as = ImmutableNumbersApiFieldError.class)
public abstract class NumbersApiFieldError {
    public abstract String getFieldName();
    public abstract String getDescription();

}
