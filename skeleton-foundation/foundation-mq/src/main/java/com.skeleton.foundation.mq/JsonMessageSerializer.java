package com.skeleton.foundation.mq;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author yxhsea
 */
public class JsonMessageSerializer implements IMessageSerializer {

    private ObjectMapper objectMapper;

    public JsonMessageSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public byte[] serialize(Object message) throws IOException {
        return this.objectMapper.writeValueAsBytes(message);
    }

    @Override
    public <T> T deserialize(byte[] messageBytes, Class<T> messageType) throws IOException {
        return this.objectMapper.readValue(messageBytes, messageType);
    }
}
