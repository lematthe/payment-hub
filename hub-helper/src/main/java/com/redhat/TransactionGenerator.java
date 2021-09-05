package com.redhat;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.smallrye.reactive.messaging.kafka.Record;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.redhat.model.*;
import java.util.Map;
import com.redhat.helpers.TransactionHelper;

@Path("/tx-gen")
public class TransactionGenerator {

    private static final Logger LOG = Logger.getLogger(TransactionGenerator.class);

    private static final String INBOUND_TX_TOPIC = "inbound-tx";
    private static final String INBOUND_ACK_TOPIC = "inbound-ack";

    @Inject
    @Channel(INBOUND_TX_TOPIC)
    Emitter<Record<String, String>> emitter;
    @Inject
    @Channel(INBOUND_ACK_TOPIC)
    Emitter<Record<String, String>> ackemitter;

    @PUT
    @Path("/sendOne")
    public void sendRecord() {
        TransactionHelper helper = new TransactionHelper();
        ObjectMapper mapper = new ObjectMapper();

        LOG.info("generateOne >>>> sending message");
        Map<String, Transaction> txs = helper.generateTransactions(1);
        Map<String, Ack> acks = helper.generateAcks();

        if(acks.size() != txs.size()){
            LOG.error("generateOne >>>> Invalid number of acks("+acks.size()+")/txs("+txs.size()+")");
        }

        txs.forEach((key, tx) -> {
            try{
                String jsonTx = mapper.writeValueAsString(tx);
                emitter.send(Record.of(key, jsonTx));
                String jsonAck = mapper.writeValueAsString(acks.get(key));
                ackemitter.send(Record.of(key, jsonAck));
            }catch( JsonProcessingException jpe){
                LOG.error("generateOne >>>> "+jpe.getMessage());
            }
        });


        helper.clearTransactions();

    }

    @PUT
    @Path("/send/{number:\\d+}")
    public void sendRecord(int number) {
        TransactionHelper helper = new TransactionHelper();
        ObjectMapper mapper = new ObjectMapper();

        LOG.info("generateMany >>>> sending messages");
        Map<String, Transaction> txs = helper.generateTransactions(number);
        Map<String, Ack> acks = helper.generateAcks();

        if(acks.size() != txs.size()){
            LOG.error("generateMany >>>> Invalid number of acks("+acks.size()+")/txs("+txs.size()+")");
        }

        txs.forEach((key, tx) -> {
            try{
                String jsonTx = mapper.writeValueAsString(tx);
                emitter.send(Record.of(key, jsonTx));
                String jsonAck = mapper.writeValueAsString(acks.get(key));
                ackemitter.send(Record.of(key, jsonAck));
            }catch( JsonProcessingException jpe){
                LOG.error("generateMany >>>> "+jpe.getMessage());
            }
        });

        helper.clearTransactions();

    }

    @PUT
    @Path("/sendNoAck")
    public void sendRecordNoAck() {
        TransactionHelper helper = new TransactionHelper();
        ObjectMapper mapper = new ObjectMapper();

        LOG.info("generateNoAck >>>> sending message with no Ack");
        Map<String, Transaction> txs = helper.generateTransactions(1);

        txs.forEach((key, tx) -> {
            try{
                String jsonTx = mapper.writeValueAsString(tx);
                emitter.send(Record.of(key, jsonTx));
            }catch( JsonProcessingException jpe){
                LOG.error("generateNoAck >>>> "+jpe.getMessage());
            }
        });

        helper.clearTransactions();
    }

    @PUT
    @Path("/sendNoTx")
    public void sendRecordNoTx() {
        TransactionHelper helper = new TransactionHelper();
        ObjectMapper mapper = new ObjectMapper();

        LOG.info("generateNoTx >>>> sending Ack with no Tx");
        Map<String, Transaction> txs = helper.generateTransactions(1);
        Map<String, Ack> acks = helper.generateAcks();

        acks.forEach((key, ack) -> {
            try{
                String jsonAck = mapper.writeValueAsString(ack);
                ackemitter.send(Record.of(key, jsonAck));
            }catch( JsonProcessingException jpe){
                LOG.error("generateNoTx >>>> "+jpe.getMessage());
            }
        });

        helper.clearTransactions();
    }
}
