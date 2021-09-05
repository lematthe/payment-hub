package com.redhat.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@RegisterForReflection
@Table(name="orphan_ack")
@Entity
public class Ack extends PanacheEntity{

    public String ackID;
    public String txID;
    public double confirmedAmount;
    public String ackNotes;
    public String ackStatus;


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
