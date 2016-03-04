package com.monsanto.tps.internal;

/**
 * Created by SMALA on 2/29/2016.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class TestKafkaConsumer {
    private final ConsumerConnector consumer;
    private final String topic;

    public TestKafkaConsumer(String zookeeper, String groupId, String topic) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeper);
        props.put("group.id", groupId);
        props.put("zookeeper.session.timeout.ms", "5000");
        props.put("zookeeper.sync.time.ms", "250");
        props.put("zk.connectiontimeout.ms", "1000000");
        props.put("zk.synctime.ms", "200");
//        props.put("autocommit.enable", "true");
//        props.put("autocommit.interval.ms", "1000");

        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
        this.topic = topic;
    }

    public void testConsumer() {
        Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, new Integer(6));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumer.createMessageStreams(topicCount);
        List<KafkaStream<byte[], byte[]>> streams = consumerStreams.get(topic);
        System.out.println("-------------------------"+streams.size());
        for (final KafkaStream stream : streams) {
            ConsumerIterator<byte[], byte[]> consumerIte = stream.iterator();
            while (consumerIte.hasNext())
                System.out.println("Message from Single Topic :: " + new String(consumerIte.next().message()));
        }
        if (consumer != null)
            consumer.shutdown();
    }

    public static void main(String[] args) {
        String topic = "lexicon-to-kafka-push-testing";
        TestKafkaConsumer testKafkaConsumer = new TestKafkaConsumer("stlutpsdkrprd01.monsanto.com:2182/kafkanp", "testgroup", topic);
        testKafkaConsumer.testConsumer();
    }
}
