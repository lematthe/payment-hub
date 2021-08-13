package com.redhat.Serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.redhat.model.Transaction;

public class TransactionSerde implements Serde<Transaction> {
    public void configure(Map<String, ?> map, boolean b) {

    }

    public void close() {

    }

    public Serializer<Transaction> serializer() {
        return new TransactionSerializer();
    }

    public Deserializer<Transaction> deserializer() {
        return new TransactionDeserializer();
    }
}
