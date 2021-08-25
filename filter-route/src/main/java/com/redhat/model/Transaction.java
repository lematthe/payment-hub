package com.redhat.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Transaction {

    private String txID;
    private String txType;
    private double requestedAmount;
    private String txState;
    private String countryCode;
    private String institutionID;

    public Transaction() {
    }

    public Transaction(String txID, String txType, double requestedAmount, String txState, String countryCode,
            String institutionID) {

        this.txID = txID;
        this.txType = txType;
        this.requestedAmount = requestedAmount;
        this.txState = txState;
        this.countryCode = countryCode;
        this.institutionID = institutionID;
    }

    public String getTxID() {
        return txID;
    }

    public void setTxID(String txID) {
        this.txID = txID;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getTxState() {
        return txState;
    }

    public void setTxState(String txState) {
        this.txState = txState;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(String institutionID) {
        this.institutionID = institutionID;
    }

    @Override
    public String toString() {
        return "Transaction { txID=" + txID + 
        ", txType="+txType+
        ", requestedAmount=" + Double.toString(requestedAmount)+
        ", txState="+txState+
        ", countryCode="+countryCode+
        ", institutionID="+institutionID+
        + '}';
    }

}
