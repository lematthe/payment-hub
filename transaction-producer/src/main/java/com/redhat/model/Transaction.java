package com.redhat.model;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Transaction {

    private String txID;
    private String txState;
    private boolean isAck;

    public Transaction(){}

    public Transaction(String txID, String txState, boolean isAck){
        this.txID = txID;
        this.txState = txState;
        this.isAck = isAck;

    }

    public String getTxID() {
        return txID;
    }
    public void setTxID(String txID) {
        this.txID = txID;
    }

    public String getTxState() {
        return txState;
    }

    public void setTxState(String txState) {
        this.txState = txState;
    }

    public boolean isAck() {
        return isAck;
    }

    public void setAck(boolean isAck) {
        this.isAck = isAck;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txID=" + txID +
                ", txState=" + txState +
                ", isAck=" + String.valueOf(isAck) +
                '}';
    }

    
}
