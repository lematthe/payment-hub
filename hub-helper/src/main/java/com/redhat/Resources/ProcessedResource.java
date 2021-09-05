package com.redhat.Resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.redhat.model.Processed;

@Path("processed")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProcessedResource {

    @GET
    public List<Processed> get(){
        return Processed.listAll();
    }
    
}
