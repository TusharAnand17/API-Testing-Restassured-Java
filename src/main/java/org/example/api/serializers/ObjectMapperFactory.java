package org.example.api.serializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class ObjectMapperFactory {
    private final static ObjectMapper OBJECT_MAPPER = create();

    private ObjectMapperFactory(){}

    private static ObjectMapper create(){
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    public static ObjectMapper get(){
        return OBJECT_MAPPER;
    }
}
