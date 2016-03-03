package com.monsanto.tps.internal;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by SMALA on 2/29/2016.
 */
public class TestKafkaProducer {

    private static Producer<Integer, String> producer;
    private final Properties props = new Properties();
    private List<Product> productList = new ArrayList<Product>();

    public TestKafkaProducer() {
//        props.put("broker.list", "localhost:9092");
        props.put("metadata.broker.list", "stlutpsdkrprd01.monsanto.com:9093");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        producer = new Producer<Integer, String>(new ProducerConfig(props));
    }

    List<Product> getProductList() {
        Product productOne = new Product();
        productOne.setId(1L);
        productOne.setCommercialName("12-8T-BLK-1333");
        productOne.setPreCommercialName("12-8T-BLK-1333");
        productOne.setCreatedDate(new Date());

        Product productTwo = new Product();
        productTwo.setId(2L);
        productTwo.setCommercialName("SBR8T15-6331");
        productTwo.setPreCommercialName("SBR8T15-6331");
        productTwo.setCreatedDate(new Date());


        Product productThree = new Product();
        productThree.setId(3L);
        productThree.setCommercialName("SBR8T12-6107");
        productThree.setPreCommercialName("SBR8T12-6107");
        productThree.setCreatedDate(new Date());

        productList.add(productOne);
        productList.add(productTwo);
        productList.add(productThree);

        return productList;

    }

    public static void main(String[] args) {
        TestKafkaProducer sp = new TestKafkaProducer();
        String topic = "lexicon-to-kafka-push-testing";
        String messageStr = "Some data from tps network";
        KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, messageStr);
        producer.send(data);
/*
        KeyedMessage<Integer, List<Product>> data = new KeyedMessage<Integer, List<Product>>(topic, sp.getProductList());
        producer.send((List<KeyedMessage<Integer, String>>) data);
*/
        producer.close();
    }
}
