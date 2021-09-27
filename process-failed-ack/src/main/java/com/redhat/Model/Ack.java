package com.redhat.Model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@RegisterForReflection
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="orphan_ack")
@Entity
public class Ack extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("ackID")
    private String ackID;
    @JsonProperty("txID")
    private String txID;
    @JsonProperty("confirmedAmount")
    private double confirmedAmount;
    @JsonProperty("ackNotes")
    private String ackNotes;
    @JsonProperty("ackStatus")
    private String ackStatus;

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
