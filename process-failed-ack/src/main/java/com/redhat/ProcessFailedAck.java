package com.redhat;

import org.jboss.logging.Logger;

import java.io.IOException;

import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.transaction.Transactional;

import com.redhat.Model.Ack;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


@Path("/")
public class ProcessFailedAck {

    private static final Logger LOG = Logger.getLogger(ProcessFailedAck.class);

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response event(String event) {
        LOG.info("Invalid Ack: " + event);

        Random rnd = new Random();
        Ack ack = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode parentNode = mapper.readTree(event);
            JsonNode root = parentNode.get("acknowledgement");
            ack = mapper.readValue(root.asText(), Ack.class);
            ack.setId(Long.valueOf(rnd.nextInt(50000)));
        } catch (IOException e) {
            LOG.info("Error: "+ e.getMessage());
            
        }
        if (null != ack){
            LOG.info("We have an ack >> "+ack.getAckID());
            ack.persistAndFlush();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/healthz")
    @Produces(MediaType.TEXT_PLAIN)
    public Response health() {
        return Response.ok().build();
    }
}

