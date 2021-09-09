package com.redhat.Beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import  com.redhat.Model.Processed;
import com.redhat.Model.Ack;
import com.redhat.Model.Transaction;

import java.io.IOException;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import org.jboss.logging.Logger;



import io.quarkus.runtime.annotations.RegisterForReflection;

@ApplicationScoped
@Named("formatdata")
@RegisterForReflection
public class FormatJson {

    private static final Logger LOG = Logger.getLogger(FormatJson.class);

    public Processed genProcessed(String json){
        Processed p = new Processed();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode parentNode = mapper.readTree(json);
            JsonNode root = parentNode.get("acknowledgement");
            Ack ack = mapper.readValue(root.asText(), Ack.class);

            JsonNode roottx= parentNode.get("transaction");
            Transaction tx = mapper.readValue(roottx.asText(), Transaction.class);

            p.setAcknowledgement(ack);
            p.setTransaction(tx);
        }catch (IOException e) {
            LOG.info("Error: "+ e.getMessage());
            
        }


        return p;

    }
    
}
