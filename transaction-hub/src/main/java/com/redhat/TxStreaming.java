package com.redhat;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
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

import java.util.HashMap;
import org.jboss.logging.Logger;

@ApplicationScoped
@SuppressWarnings("deprecation")
public class TxStreaming {

    private static final Logger LOG = Logger.getLogger(TxStreaming.class);

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

        StoreBuilder<KeyValueStore<String, String>> txStore = Stores
                .keyValueStoreBuilder(Stores.persistentKeyValueStore(TX_STORE), Serdes.String(), Serdes.String())
                .withLoggingEnabled(new HashMap<>());

        // Inbound Acknowledgement Queue which will be joined
        KStream<String, String> txs = builder.stream(INBOUND_TX_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));

        // Initial Builder Stream to join inbound_tx with inbound_ack and send it to the
        // processed queue
        builder.stream(INBOUND_ACK_TOPIC, Consumed.with(Serdes.String(), Serdes.String()))
                .outerJoin(txs, (ack, transaction ) -> {
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
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String())).reduce(((key, lastValue) -> lastValue))
                .toStream().to("outbound-ack", Produced.with(Serdes.String(), Serdes.String()));

        final Topology topology = builder.build();

        // Attach the messages from the Join Queue to a Source and name it InboundTX
        topology.addSource("InboundTX", new StringDeserializer(), new StringDeserializer(), "outbound-ack");

        // Attach the processor to the flow, and attach the Source created above to it.

        topology.addProcessor("TransactionProcessor", new ProcessorSupplier<String, String>() {
            public Processor<String, String> get() {
                return new PaymentProcessor();
            }
        }, "InboundTX");

        // Send the processed messages to the Outbound Queue,
        // when they are "done" in the Processor
        topology.addSink("Sink", PROCESSED_TRANSACTIONS, new StringSerializer(), new StringSerializer(),
                "TransactionProcessor");

        // Add the state store
        topology.addStateStore(txStore, "TransactionProcessor");

        return topology;
    }

}
