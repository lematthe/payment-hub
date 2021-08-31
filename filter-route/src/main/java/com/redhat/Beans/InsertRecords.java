package com.redhat.Beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.redhat.model.Processed;

import io.quarkus.runtime.annotations.RegisterForReflection;

@ApplicationScoped
@Named("gensql")
@RegisterForReflection
public class InsertRecords {
    public String generatesql(Processed pTx){
        return "INSERT INTO processed (txid, txType, requestedAmount, txState, countryCode, institutionID, ackid, ackNotes, ackStatus) VALUES ("+
        "\""+pTx.getTransaction().getTxID()+"\","+
        "\""+pTx.getTransaction().getTxType()+"\","+
        pTx.getTransaction().getRequestedAmount()+","+
        "\""+pTx.getTransaction().getTxState()+"\","+
        "\""+pTx.getTransaction().getCountryCode()+"\","+
        "\""+pTx.getTransaction().getInstitutionID()+"\","+
        "\""+pTx.getAcknowledgement().getAckID()+"\","+
        "\""+pTx.getAcknowledgement().getAckNotes()+"\","+
        "\""+pTx.getAcknowledgement().getAckStatus()+"\")";                   
    }
    
}
