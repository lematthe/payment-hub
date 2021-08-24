package com.redhat.Serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

import com.redhat.model.Ack;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AckDeserializer implements Deserializer<Ack>{
    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> map, boolean b) {

    }

    public Ack deserialize(String s, byte[] bytes) {

        try {
            if(bytes == null || bytes.length == 0){
                return null;
            }
            return mapper.readValue(bytes, Ack.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}