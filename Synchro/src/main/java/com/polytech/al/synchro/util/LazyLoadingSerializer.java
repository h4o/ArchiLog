package com.polytech.al.synchro.util;
import java.io.IOException;

import org.springframework.data.mongodb.core.convert.LazyLoadingProxy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LazyLoadingSerializer extends JsonSerializer<LazyLoadingProxy> {

    @Override
    public void serialize(LazyLoadingProxy value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("id", value.toDBRef().getId().toString());
        jgen.writeEndObject();
    }
}