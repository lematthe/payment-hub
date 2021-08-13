package com.redhat.Serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.redhat.model.AckedTransaction;

public class AckedTransactionSerde implements Serde<AckedTransaction> {
    public void configure(Map<String, ?> map, boolean b) {

    }

    public void close() {

    }

    public Serializer<AckedTransaction> serializer() {
        return new AckedTransactionSerializer();
    }

    public Deserializer<AckedTransaction> deserializer() {
        return new AckedTransactionDeserializer();
    }
}
