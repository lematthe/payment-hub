package com.redhat.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Ack {

    
    private String id;
    private String ackID;
    private String txID;
    private double confirmedAmount;
    private String ackNotes;
    private String ackStatus;

    public Ack() {
    }

    public Ack(String id, String ackID, String txID, double confirmedAmount, String ackNotes, String ackStatus) {
        this.id = id;
        this.ackID = ackID;
        this.txID = txID;
        this.confirmedAmount = confirmedAmount;
        this.ackNotes = ackNotes;
        this.ackStatus = ackStatus;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getAckID() {
        return ackID;
    }

    public void setAckID(String ackID) {
        this.ackID = ackID;
    }

    public String getTxID() {
        return txID;
    }

    public void setTxID(String txID) {
        this.txID = txID;
    }

    public double getConfirmedAmount() {
        return confirmedAmount;
    }

    public void setConfirmedAmount(double confirmedAmount) {
        this.confirmedAmount = confirmedAmount;
    }

    public String getAckNotes() {
        return ackNotes;
    }

    public void setAckNotes(String ackNotes) {
        this.ackNotes = ackNotes;
    }

    public String getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(String ackStatus) {
        this.ackStatus = ackStatus;
    }

    @Override
    public String toString() {
        return "Ack{" + "ackID=" + ackID + 
        ", txID=" + txID + 
        ", confirmedAmount=" + Double.toString(confirmedAmount)+
        ", ackNotes="+ackNotes+
        ", ackStatus="+ackStatus
        + '}';
    }
    @JsonCreator
    public static Ack Create(String jsonString) {

        Ack ack = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            ack = mapper.readValue(jsonString, Ack.class);
        } catch (IOException e) {
            // handle
            
        }

        return ack;
    }
}
