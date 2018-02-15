package com.edusancon.wewac.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.Serializable;

public enum JsonSerializer {
    PRETTY{
        @Override
        ObjectWriter getWriter() {
            ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writerWithDefaultPrettyPrinter();
        }
    },
    ONE_LINE{
        @Override
        ObjectWriter getWriter() {
            ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writer();
        }
    };

    private final ObjectWriter writer;

    JsonSerializer(){
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        writer = getWriter();
    }

    abstract ObjectWriter getWriter();

    public String serialize(Serializable object){
        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
