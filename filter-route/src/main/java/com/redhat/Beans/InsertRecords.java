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
        System.out.println("Process1 >>> "+pTx.getTransaction().getTxID());
        System.out.println("Process2 >>> "+pTx.getTransaction().getTxType());

        System.out.println("Process3 >>> "+pTx.getTransaction().getRequestedAmount());
        System.out.println("Process4 >>> "+pTx.getTransaction().getTxState());
        System.out.println("Process5 >>> "+pTx.getTransaction().getCountryCode());
        System.out.println("Process6 >>> "+pTx.getTransaction().getInstitutionID());
        System.out.println("Process7 >>> "+pTx.getAcknowledgement().getAckID());
        System.out.println("Process8 >>> "+pTx.getAcknowledgement().getAckNotes());       
        System.out.println("Process9 >>> "+pTx.getAcknowledgement().getAckStatus());

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
