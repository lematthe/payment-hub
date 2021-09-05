package com.redhat;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyDescription;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.state.Stores;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.state.KeyValueStore;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.redhat.Processor.PaymentProcessor;

import java.time.Duration;
import java.util.HashMap;
import org.jboss.logging.Logger;

@ApplicationScoped
@SuppressWarnings("deprecation")
public class TxStreaming {

    private static final Logger LOG = Logger.getLogger(TxStreaming.class);

    /*
     * Attach the values from application.properties to various TOPIC names for use
     * in the Topology
     */
    @ConfigProperty(name = "inbound.tx_topic")
    public String INBOUND_TX_TOPIC;

    @ConfigProperty(name = "inbound.ack_topic")
    public String INBOUND_ACK_TOPIC;

    @ConfigProperty(name = "outbound.acked_topic")
    public String OUTBOUND_ACKED_TOPIC;

    @ConfigProperty(name = "outbound.process_topic")
    public String PROCESSED_TRANSACTIONS;

    @ConfigProperty(name = "tx_store")
    public String TX_STORE;

    @Produces
    public Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        /*
         * The K,V store for saving the 'orphaned' TX or ACK if both parts are not
         * JOINED in the intial topology
         */
        StoreBuilder<KeyValueStore<String, String>> txStore = Stores
                .keyValueStoreBuilder(Stores.persistentKeyValueStore(TX_STORE), Serdes.String(), Serdes.String())
                .withLoggingEnabled(new HashMap<>());

        // Inbound Acknowledgement Queue which will be joined
        KStream<String, String> txs = builder.stream(INBOUND_TX_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));

        /*
         * The initial topology is going to trigger when either an ACK or TX is placed
         * on the appropriate topic It will then attempt a JOIN before streaming it to
         * the outbound topic
         */
        builder.stream(INBOUND_ACK_TOPIC, Consumed.with(Serdes.String(), Serdes.String()))
                .outerJoin(txs, (ack, transaction) -> {
                    String ackedMsg = new String();
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectNode json = mapper.createObjectNode();
                    try {
                        if (null != transaction) {
                            LOG.info("stream >>>> Found Transaction");
                            json.put("transaction", transaction);
                        }

                        if (null != ack) {
                            LOG.info("stream >>>> Found Acknowledgement");
                            json.put("acknowledgement", ack);
                        }
                        ackedMsg = mapper.writeValueAsString(json);

                    } catch (com.fasterxml.jackson.core.JsonProcessingException je) {
                        LOG.error("Error : " + je.getMessage());
                    }
                    LOG.info("stream >>>> Processing Message");
                    return ackedMsg;
                }, JoinWindows.of(Duration.ofSeconds(10)),
                        Joined.with(Serdes.String(), Serdes.String(), Serdes.String()))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(((key, lastValue) -> lastValue))
                .toStream()
                .to("outbound-ack", Produced.with(Serdes.String(), Serdes.String()));

        final Topology topology = builder.build();

        /* Messages that consumed during the JOIN will be passed to the nexy topic */
        topology.addSource("InboundTX", new StringDeserializer(), new StringDeserializer(), "outbound-ack");

        /*
         * From the Source above, the processor will execute and based on whether the
         * JOIN succeeded will either forward on the message to the SINK (if it has both
         * the TX and ACK), determine if the missing part of the TX/ACK is stored and
         * forward them both on or store in the K,V store for handling later
         */

        topology.addProcessor("TransactionProcessor", new ProcessorSupplier<String, String>() {
            public Processor<String, String> get() {
                return new PaymentProcessor();
            }
        }, "InboundTX");

        /*
         * When the message has been processed by the TransactionProcessor, the commit
         * function will automatically wirte it to the topic below.
         */
        topology.addSink("Sink", PROCESSED_TRANSACTIONS, new StringSerializer(), new StringSerializer(),
                "TransactionProcessor");

        // Add the state store
        topology.addStateStore(txStore, "TransactionProcessor");

        /*
         * Topology description will display the sub topologies to give a sense of the
         * flow through the streaming application.
         */
        TopologyDescription td = topology.describe();
        LOG.info(td.toString());

        return topology;
    }

}
