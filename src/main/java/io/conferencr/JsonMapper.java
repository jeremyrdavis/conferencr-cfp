package io.conferencr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Record record) {

        try {
            return objectMapper.writeValueAsString(record);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
