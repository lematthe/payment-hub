package com.redhat.Resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.redhat.model.Transaction;

@Path("tx")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TxResource {

    @GET
    public List<Transaction> get(){
        return Transaction.listAll();
    }
    
}
