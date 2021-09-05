package com.redhat.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@RegisterForReflection
@Entity
@Table(name="orphan_ack")
public class Ack extends PanacheEntity{

    public String ackID;
    public String txID;
    public double confirmedAmount;
    public String ackNotes;
    public String ackStatus;


    public Ack() {
    }

    public Ack(String ackID, String txID, double confirmedAmount, String ackNotes, String ackStatus) {
        this.ackID = ackID;
        this.txID = txID;
        this.confirmedAmount = confirmedAmount;
        this.ackNotes = ackNotes;
        this.ackStatus = ackStatus;
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
}
