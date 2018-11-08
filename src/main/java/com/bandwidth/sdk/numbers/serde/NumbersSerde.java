package com.bandwidth.sdk.numbers.serde;

import static com.bandwidth.sdk.numbers.exception.ExceptionUtils.catchClientExceptions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;


public class NumbersSerde {

    private static final XmlMapper mapper = new XmlMapper();

    public <T> T deserialize(String messageBody, TypeReference<T> clazz) {
        return catchClientExceptions(() -> mapper.readValue(messageBody, clazz));
    }

    public <T> T deserialize(String messageBody, Class<T> clazz) {
        return catchClientExceptions(() -> mapper.readValue(messageBody, clazz));
    }

    public <T> String serialize(T objectToMap) {
        return catchClientExceptions(() -> mapper.writeValueAsString(objectToMap));
    }

}

