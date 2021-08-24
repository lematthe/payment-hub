package com.redhat.Processor;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.Duration;

@SuppressWarnings("unchecked")
public class PaymentProcessor implements Processor<String, String> {

    private ProcessorContext context;
    private KeyValueStore<String, String> txStore;

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        txStore = (KeyValueStore<String, String>) context.getStateStore("tx-missing-store");
        // schedule a punctuate() method every 10 seconds based on stream-time
        this.context.schedule(Duration.ofSeconds(60), PunctuationType.WALL_CLOCK_TIME, new Punctuator() {

            @Override
            public void punctuate(long timestamp) {
                System.out.println("Scheduled punctuator called at " + timestamp);
                KeyValueIterator<String, String> iter = txStore.all();
                while (iter.hasNext()) {
                    KeyValue<String, String> entry = iter.next();
                    System.out.println("Orphaned Message being forwarded: "+entry.key.toString());
                    context.forward(entry.key, entry.value.toString());
                    txStore.put(entry.key, null);
                }
                iter.close();

                // commit the current processing progress
                context.commit();
            }
        });

    }

    @Override
    public void process(String key, String value) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode parentNode = mapper.readTree(value);
            if(!parentNode.has("transaction") || !parentNode.has("acknowledgement")){
                //We have a missing Transaction or Acknowledgement
                if (txStore.get(key) != null) {
                    //Missing TX or Ack is already in the store
                    String storedValue = txStore.get(key);
                    ObjectNode parentRoot = parentNode.deepCopy();
                    if(parentNode.has("transaction")){
                        //Create the ACK from the store
                        parentRoot.put("acknowledgement",storedValue );
                    }else{
                        //Create the TX from the store
                        parentRoot.put("transaction",storedValue );
                    }
                    // Delete the old entry from the store
                    txStore.delete(key);
                    //Forward the combined message to the outbound queue
                    context.forward(key, mapper.writeValueAsString(parentRoot));
                } else {
                    // add to state store as either left or right data is missing
                    System.out.println("MSGPROC01 : storing : "+key+":"+value);
                    txStore.put(key, value);
                }
            } else {
                if(txStore.get(key) != null){
                    txStore.delete(key);
                }

                // We have the complete value already from the JOIN
                context.forward(key, value);
            }
            context.commit();
        } catch (com.fasterxml.jackson.core.JsonProcessingException je) {
            //Invalid Transaction
            System.out.println("Invalid Message "+je.getMessage());
        }
    }

}
