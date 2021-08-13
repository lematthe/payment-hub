package com.redhat.Serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.redhat.model.Ack;

public class AckSerde implements Serde<Ack> {
    public void configure(Map<String, ?> map, boolean b) {

    }

    public void close() {

    }

    public Serializer<Ack> serializer() {
        return new AckSerializer();
    }

    public Deserializer<Ack> deserializer() {
        return new AckDeserializer();
    }
}
