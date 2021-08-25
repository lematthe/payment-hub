package com.redhat.model;

public class Processed {

    private Transaction transaction;
    private Ack acknowledgement;

    public Processed() {
    }

    public Processed(Transaction transaction, Ack acknowledgement) {
        if (null != acknowledgement) {
            this.acknowledgement = acknowledgement;
        }

        if (null != transaction) {
            this.transaction = transaction;
        }
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Ack getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(Ack acknowledgement) {
        this.acknowledgement = acknowledgement;
    }
}
