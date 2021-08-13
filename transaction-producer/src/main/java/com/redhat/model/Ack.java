package com.redhat.model;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Ack {

    private String ackID;
    private String txID;
    private String status;

    public Ack(){}

    public Ack(String ackID, String status, String txID){
        this.ackID = ackID;
        this.txID = txID;
        this.status = status;
    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Ack{" +
                "ackID=" + ackID +
                ", txID=" + txID +
                ", status=" + status +
                '}';
    }
}
