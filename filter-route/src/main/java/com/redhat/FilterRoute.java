package com.redhat;

import javax.enterprise.context.ApplicationScoped;

import com.redhat.Model.Processed;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

@ApplicationScoped
public class FilterRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        from("kafka:outbound-processed?brokers=payment-kafka-kafka-bootstrap.payment-hub.svc:9092")
        .routeId("ProcessedFilter")
        .choice()
            .when().jsonpath("$.transaction", true)
                .choice()
                    .when().jsonpath("$.acknowledgement", true)
                        .log("${body}")
                        .bean("formatdata","genProcessed")
                        .bean("gensql", "generatesql")
                        .log("${body}")
                        .to("sql:ignored?useMessageBodyForSql=true")
                    .otherwise()
                        .log("NO ACK")
                        .log("${body}")
                        .to("kafka:invalid-transactions?brokers=payment-kafka-kafka-bootstrap.payment-hub.svc:9092")
                .endChoice()
            .when().jsonpath("$.acknowledgement")
                .log("No TX")
                .log("${body}")
                .to("kafka:invalid-acks?brokers=payment-kafka-kafka-bootstrap.payment-hub.svc:9092")
            .otherwise()
                .log("NONE")
        .end();
        
    }
}