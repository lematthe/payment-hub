package com.redhat.Processor;

import org.jboss.logging.Logger;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.Duration;

@SuppressWarnings("unchecked")
public class PaymentProcessor implements Processor<String, String> {

    private static final Logger LOG = Logger.getLogger(PaymentProcessor.class);

    private ProcessorContext context;
    private KeyValueStore<String, String> txStore;

    @Override
    public void close() {

    }

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        txStore = (KeyValueStore<String, String>) context.getStateStore("tx-missing-store");
        // schedule a punctuate() method every 10 seconds based on stream-time
        this.context.schedule(Duration.ofSeconds(60), PunctuationType.WALL_CLOCK_TIME, new Punctuator() {

            @Override
            public void punctuate(long timestamp) {
                LOG.info("processor >>>> Scheduled punctuator called at " + timestamp);
                KeyValueIterator<String, String> iter = txStore.all();
                while (iter.hasNext()) {
                    KeyValue<String, String> entry = iter.next();
                    LOG.info("processor >>>> Orphaned Message being forwarded: " + entry.key.toString());
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
            if (!parentNode.has("transaction") || !parentNode.has("acknowledgement")) {
                // We have a missing Transaction or Acknowledgement
                if (txStore.get(key) != null) {
                    // Missing TX or Ack is already in the store
                    String storedValue = txStore.get(key);
                    ObjectNode parentRoot = parentNode.deepCopy();
                    if (parentNode.has("transaction")) {
                        // Create the ACK from the store
                        LOG.info("processor >>>> Found acknowledgement in store");
                        parentRoot.put("acknowledgement", storedValue);
                    } else {
                        // Create the TX from the store
                        LOG.info("processor >>>> Found transaction in store");
                        parentRoot.put("transaction", storedValue);
                    }
                    // Delete the old entry from the store
                    txStore.delete(key);
                    // Forward the combined message to the outbound queue
                    context.forward(key, mapper.writeValueAsString(parentRoot));
                } else {
                    // add to state store as either left or right data is missing
                    LOG.info("processor >>>> storing (" + key + ":" + value + ")");
                    txStore.put(key, value);
                }
            } else {
                if (txStore.get(key) != null) {
                    txStore.delete(key);
                }

                LOG.info("processor >>>> We have both the tx and ack");
                // We have the complete value already from the JOIN
                context.forward(key, value);
            }
            context.commit();
        } catch (com.fasterxml.jackson.core.JsonProcessingException je) {
            // Invalid Transaction
            LOG.error("processor >>>> Invalid Message " + je.getMessage());
        }
    }

}
