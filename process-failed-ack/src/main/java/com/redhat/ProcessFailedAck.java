package com.redhat;

import org.jboss.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class ProcessFailedAck {

    private static final Logger LOG = Logger.getLogger(ProcessFailedAck.class);

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response event(String event) {
        LOG.info("Invalid Ack: " + event);
        return Response.ok().build();
    }

    @GET
    @Path("/healthz")
    @Produces(MediaType.TEXT_PLAIN)
    public Response health() {
        return Response.ok().build();
    }
}
