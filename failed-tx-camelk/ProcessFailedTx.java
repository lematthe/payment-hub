// camel-k: language=java property=file:database.properties 
// camel-k: dependency=camel:jacksonxml 
// camel-k: dependency=camel:sql 
// camel-k: dependency=mvn:mysql:mysql-connector-java:8.0.21
// camel-k: dependency=mvn:org.apache.commons:commons-dbcp2:jar:2.8.0
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.IOException;
import javax.inject.Inject;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import io.quarkus.runtime.annotations.RegisterForReflection;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

public class ProcessFailedTx extends RouteBuilder {


  @Override
  public void configure() throws Exception {

    Processor insertDB = new DBProcessor();

    // Write your routes here, for example:
    from("kafka:invalid-transactions?brokers=payment-kafka-kafka-bootstrap.payment-hub.svc:9092")
        .routeId("invalid-transactions")
        .process(insertDB)
        .to("log:info?showBody=true")
        .log("INSERT INTO orphan_tx (id, txID, txType, requestedAmount, countryCode, institutionID) VALUES (${body[transaction][id]}. ${body[transaction][txID]}, ${body[transaction][txType]}, ${body[transaction][requestedAmount]}, ${body[transaction][countryCode]}, ${body[transaction][institutionID]})")
        .setBody(simple("INSERT INTO orphan_tx (id, txID, txType, requestedAmount, countryCode, institutionID) VALUES (${body[transaction][id]}, ${body[transaction][txID]},"+ 
        "'${body[transaction][txType]}', ${body[transaction][requestedAmount]}, '${body[transaction][countryCode]}', '${body[transaction][institutionID]}')"))
          .to("jdbc:mysqlBean");

  }

  private final class DBProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
      @SuppressWarnings("unchecked")
      Random rnd = new Random();
      ObjectMapper mapper = new ObjectMapper();
      try {
          JsonNode parentNode = mapper.readTree(exchange.getMessage().getBody(String.class));

          Iterator<String> it = parentNode.fieldNames();
          while(it.hasNext()){
            String name = it.next();
            JsonNode root = parentNode.get(name);

            JsonNode tx = mapper.readTree(root.asText());

            Iterator<String> in = tx.fieldNames();
            Map<String, Object> outputBody = new HashMap<String, Object>();

            outputBody.put("id", rnd.nextInt(500000));
            outputBody.put("txID", tx.get("txID"));
            outputBody.put("txType", tx.get("txType"));
            outputBody.put("requestedAmount", tx.get("requestedAmount"));
            outputBody.put("countryCode", tx.get("countryCode"));
            outputBody.put("institutionID",tx.get("institutionID"));
  
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("transaction", outputBody);
  
            exchange.getIn().setBody(res);
          }
      }catch(IOException ioe){
        System.out.println("exception : "+ioe.getMessage());
      }
    }
  }
}
