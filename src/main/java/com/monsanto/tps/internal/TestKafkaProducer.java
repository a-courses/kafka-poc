package com.monsanto.tps.internal;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by SMALA on 2/29/2016.
 */
public class TestKafkaProducer {

    private static Producer<Integer, String> producer;
    private final Properties props = new Properties();

    public TestKafkaProducer() {
        props.put("broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        producer = new Producer<Integer, String>(new ProducerConfig(props));
    }

    public static void main(String[] args) {
        TestKafkaProducer sp = new TestKafkaProducer();
        String topic = (String) args[0];
        String messageStr = (String) args[1];
        KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, messageStr);
        producer.send(data);
        producer.close();
    }
}
