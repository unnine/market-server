package com.market.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonEncoder {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String encode(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

}