package com.redhat.Model;

import org.jboss.logging.Logger;
import com.redhat.Model.Transaction;
import com.redhat.Model.Ack;

public class Processed {

    private Transaction transaction;
    private Ack acknowledgement;

    private static final Logger LOG = Logger.getLogger(Processed.class);

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
