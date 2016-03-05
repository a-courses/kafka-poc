package com.monsanto.tps.internal;

import com.google.gson.Gson;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.*;

/**
 * Created by SMALA on 2/29/2016.
 */
public class TestKafkaProducer {

    private static Producer<String, String> producer;
    private final Properties props = new Properties();
    private List<Product> productList = new ArrayList<Product>();

//    private String metadataBrokerList = '''stlutpsdkrprd02.monsanto.com:9092,stlutpsdkrprd01.monsanto.com:9094,stlutpsdkrprd01.monsanto.com:9093,stlutpsdkrprd01.monsanto.com:9092,stlutpsdkrprd02.monsanto.com:9094,stlutpsdkrprd02.monsanto.com:9093''';

    public TestKafkaProducer() {
//        props.put("broker.list", "localhost:9092");
        props.put("metadata.broker.list", "stlutpsdkrprd02.monsanto.com:9092,\n" +
                "stlutpsdkrprd01.monsanto.com:9094,\n" +
                "stlutpsdkrprd01.monsanto.com:9093,\n" +
                "stlutpsdkrprd01.monsanto.com:9092,\n" +
                "stlutpsdkrprd02.monsanto.com:9094,\n" +
                "stlutpsdkrprd02.monsanto.com:9093,");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        producer = new Producer<String, String>(new ProducerConfig(props));
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

        Product productFour = new Product();
        productFour.setId(4L);
        productFour.setCommercialName("12-8T-BLK-1676CML");
        productFour.setPreCommercialName("12-8T-BLK-1676CML");
        productFour.setCreatedDate(new Date());

        productList.add(productOne);
        productList.add(productTwo);
        productList.add(productThree);
        productList.add(productFour);

        return productList;

    }

    public static void main(String[] args) {
        TestKafkaProducer sp = new TestKafkaProducer();
        String topic = "lexicon-to-kafka-push-testing";
        String productData = new Gson().toJson(sp.getProductList());
//        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, productData);
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, UUID.randomUUID().toString(), productData);
        producer.send(data);
        producer.close();
    }
}
