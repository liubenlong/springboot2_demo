package com.example.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.internals.SubscriptionState;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.requests.IsolationLevel;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

public class kafkaTest {

    public static void main(String[] args) throws Exception {
        Properties p = new Properties();
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.52.4:9092,172.16.52.5:9092,172.16.52.6:9092");
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.GROUP_ID_CONFIG, "flink");

        BaseZookeeper zookeeper = new BaseZookeeper();
        zookeeper.connectZookeeper("172.16.52.4:2181,172.16.52.5:2181,172.16.52.6:2181");

        List<String> children = zookeeper.getChildren("/");
        System.out.println(children);


        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);
        List<TopicPartition> tps = new ArrayList<>();
        TopicPartition tp0 = new TopicPartition("stanlee.dataservice.master.replication.messages.phoenix", 0);
        TopicPartition tp1 = new TopicPartition("stanlee.dataservice.master.replication.messages.phoenix", 1);
        tps.add(tp0);
        tps.add(tp1);
        Map<TopicPartition, Long> map = kafkaConsumer.endOffsets(tps);
        System.out.println(JSONObject.toJSONString(map));
//        Map<TopicPartition, Long> map1 = kafkaConsumer.beginningOffsets(tps);
//        System.out.println(JSONObject.toJSONString(map1));



        String data = zookeeper.getData("/consumers/flink/offsets/stanlee.dataservice.master.replication.messages.phoenix/0");
        System.out.println(data);


//
//        org.apache.kafka.clients.consumer.internals.SubscriptionState subscriptionState = new SubscriptionState();
//        while (true) {
//            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.println(String.format("topic:%s,offset:%d,消息:%s", //
//                        record.topic(), record.offset(), record.value()));
//            }
//        }
    }
}
