package com.redhat.Beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import  com.redhat.Model.Processed;

import java.util.Random;



import io.quarkus.runtime.annotations.RegisterForReflection;


@ApplicationScoped
@Named("gensql")
@RegisterForReflection
public class InsertRecords {

    public String generatesql(Processed pTx){

        if(pTx.getAcknowledgement() == null){
            System.out.println("Null ACK!!!!");
        }

        Random rnd = new Random();

        return "INSERT INTO processed (id, txid, txType, requestedAmount, txState, countryCode, institutionID, ackid, ackNotes, ackStatus) VALUES ("+
        rnd.nextInt(500000)+","+
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
