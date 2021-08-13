package com.redhat.model;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AckedTransaction {
    private String txID;
    private String ackID;
    private String status;
    private String txState;
    private boolean isAck;

    public AckedTransaction(String txID, String ackID, String status, String txState, boolean ack){
        this.txID = txID;
        this.ackID = ackID;
        this.status = status;
        this.txState = txState;
        this.isAck = ack;
    }
    public AckedTransaction(Transaction tx, Ack ack){
        if( null != tx ){
            this.txID = tx.getTxID();
            this.txState = tx.getTxState();
            this.isAck = tx.isAck();
        }
        if (null != ack){
            this.ackID = ack.getAckID();
            this.status = ack.getStatus();
        }
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
        return "AckedTransaction{" +
                "txID=" + txID +
                ", ackID=" + ackID +
                ", status=" + status +
                ", txState=" + txState +
                ", isAck=" + String.valueOf(isAck) +
                '}';
    }
    
}
