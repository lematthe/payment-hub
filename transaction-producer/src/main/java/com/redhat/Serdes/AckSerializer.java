package com.redhat.Serdes;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.redhat.model.Ack;

import org.apache.kafka.common.serialization.Serializer;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AckSerializer implements Serializer<Ack>{
    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String s, Ack event) {
        try {
            return mapper.writeValueAsBytes(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}