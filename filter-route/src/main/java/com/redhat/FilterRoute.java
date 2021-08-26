package com.redhat;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.redhat.model.*;

@ApplicationScoped
public class FilterRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        from("kafka:outbound-processed?brokers=payment-kafka-kafka-bootstrap.payment-infra.svc:9092")
        .routeId("ProcessedFilter")
        .choice()
            .when().jsonpath("$.transaction", true)
                .choice()
                    .when().jsonpath("$.acknowledgement", true)
                        .unmarshal()
                        .json(JsonLibrary.Jackson, Processed.class)
                        .bean("myNamedBean", "gensql")
                        .log("${body}")
                        .to("sql:${body}")
                    .otherwise()
                        .log("NO ACK")
                .endChoice()
            .when().jsonpath("$.acknowledgement")
                .log("No TX")
            .otherwise()
                .log("NONE")
        .end();
        
    }
    
}
