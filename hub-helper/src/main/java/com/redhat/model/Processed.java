package com.redhat.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@RegisterForReflection
@Entity
@Table(name="processed")
public class Processed extends PanacheEntity{
    public String txid;
    public String txType;
    public double requestedAmount;
    public String txState;
    public String countryCode;
    public String institutionID;
    public String ackid;
    public String ackNotes;
    public String ackStatus;
    
}
