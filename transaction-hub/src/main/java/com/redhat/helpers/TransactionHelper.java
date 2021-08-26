package com.redhat.helpers;

import com.redhat.model.*;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.Random;

import org.jboss.logging.Logger;

public class TransactionHelper {

    private static Map<String, Transaction> transactions = new HashMap<String, Transaction>();
    private static Map<String, Ack> acks = new HashMap<String, Ack>();

    private static final Logger LOG = Logger.getLogger(TransactionHelper.class);

    public TransactionHelper(){}

    public  Map<String, Transaction> generateTransactions(int numberOfTx) {
        for(int i=0; i< numberOfTx; i++){
            Transaction tx = new Transaction();
            tx.setTxID(this.generateTxID());
            tx.setInstitutionID(this.generateInsitution());
            tx.setRequestedAmount(this.generateAmount(1, 100000));
            tx.setCountryCode(this.generateCountryCode());
            tx.setTxType(this.generateType());
            tx.setTxState(this.generateState());

            transactions.put(tx.getTxID(), tx);

            LOG.info("helper > Adding Transaction #"+i);


        }

        return transactions;

    }

    public  String generateTxID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public  double generateAmount(int min, int max){
        Random r = new Random();
        return min + r.nextDouble() * (max - min);
    }

    public  String generateInsitution(){
        String name = new String();
        Random rnd = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int lengthOfName = rnd.nextInt(15)+1;

        for(int i=0; i<lengthOfName; i++ ){
            name += (alphabet.charAt(rnd.nextInt(alphabet.length())));
        }

        return name;
    }

    public  String generateCountryCode(){
        String[] codes = {"US","UK","FR","DE","IT","CH"};

        Random rnd = new Random();
        return codes[rnd.nextInt(codes.length -1)];
    }
    public  String generateType(){
        String[] types = {"APPROVE","DEPOSIT","APPROVE_AND_DEPOSIT","CREDIT","REVERSE_APPROVAL","REVERSE_DEPOSIT","REVERSE_CREDIT"};

        Random rnd = new Random();
        return types[rnd.nextInt(types.length -1)];
    }

    public  String generateState(){
        String[] states = {"NEW","PENDING","SUCCESSFUL","FAILED","CANCELLED"};

        Random rnd = new Random();
        return states[rnd.nextInt(states.length -1)];
    }

    public  String generateAckState(){
        String[] ackState = {"ACKED","FAILED","MISSING"};

        Random rnd = new Random();
        return ackState[rnd.nextInt(ackState.length -1)];
    }

    public  void clearTransactions(){
        transactions.clear();
        acks.clear();
    }

    public  Map<String, Ack> generateAcks(){
        transactions.forEach((key, tx) -> {
                Ack ack = new Ack(); 
                ack.setAckID(this.generateTxID());
                ack.setConfirmedAmount(tx.getRequestedAmount());
                ack.setAckStatus(this.generateAckState());
                ack.setAckNotes("None");

                acks.put(tx.getTxID(), ack);

                LOG.info("helper > Adding Ack");

        });

        return acks;
    }

}
