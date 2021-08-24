package com.redhat;

import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.smallrye.reactive.messaging.kafka.Record;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.redhat.model.*;



@Path("/tx-gen")
public class TransactionGenerator {
    
    private static final String INBOUND_TX_TOPIC="inbound-tx" ;
    private static final String INBOUND_ACK_TOPIC="inbound-ack";

    @Inject @Channel(INBOUND_TX_TOPIC) Emitter<Record<String, String>> emitter;
    @Inject @Channel(INBOUND_ACK_TOPIC) Emitter<Record<String, String>> ackemitter;
    
    private Random random = new Random();


 

    @PUT
    @Path("/sendOne")
    public void sendRecord(){

        System.out.println("Sending.....");
        ObjectMapper mapper = new ObjectMapper();
        String txID = "TXID"+String.valueOf(random.nextGaussian() * 15);
        Transaction tx = new Transaction();
        String txKey = String.valueOf(random.nextGaussian() * 15);
        tx.setTxID(txID);
        tx.setTxState("Inflight");
        tx.setAck(false);
        try {
            String jsonTx = mapper.writeValueAsString(tx);
            emitter.send(Record.of(txKey, jsonTx));
            System.out.println("Sent Transaction");
        } catch (com.fasterxml.jackson.core.JsonProcessingException je){
            System.out.println("Tx Error :" + je.getMessage());
        }

        Ack ack = new Ack();
        ack.setAckID("ACKID"+String.valueOf(random.nextGaussian() * 15));
        ack.setTxID(txID);
        ack.setStatus("ACK");
        try {
            String jsonAck = mapper.writeValueAsString(ack);
            ackemitter.send(Record.of(txKey, jsonAck));
            System.out.println("Sent Ack");
        } catch (com.fasterxml.jackson.core.JsonProcessingException je){
            System.out.println("Ack Error :" + je.getMessage());
        }
        System.out.println("Sent.....");

    }

    // public Record<String, String> sendTransaction() {
    //     String txID = String.valueOf(random.nextGaussian() * 15);
    //     String txKey = "TXID"+String.valueOf(random.nextGaussian() * 15);

    //     System.out.println("Writing message");

    //     return Record.of(txKey, txID);

    // }

}
