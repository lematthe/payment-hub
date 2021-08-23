package com.redhat.Serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

import com.redhat.model.AckedTransaction;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AckedTransactionDeserializer implements Deserializer<AckedTransaction>{
    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> map, boolean b) {

    }

    public AckedTransaction deserialize(String s, byte[] bytes) {

        try {
            if(bytes == null || bytes.length == 0){
                return null;
            }
            return mapper.readValue(bytes, AckedTransaction.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}