package com.example.service;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OrderMsgConsumerTest {


    public static void main(String[] args) throws Exception {
        normal();//普通消费

//        order();//顺序消费
    }

    private static void normal() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setNamesrvAddr("node1:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicTest", "*");
        consumer.setConsumeThreadMin(3);
        consumer.setConsumeThreadMax(6);

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
//                    System.out.println("收到消息," + new String(msg.getBody()));
                System.out.println("msgId:"+msg.getMsgId() + ",queueId:"+msg.getQueueId()+",orderId:"+new String(msg.getBody())+",i:"+msg.getKeys());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }


    private static void order() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setNamesrvAddr("node1:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicTest", "*");
        //消费者并行消费
        consumer.setConsumeThreadMin(3);
        consumer.setConsumeThreadMax(6);

        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
//                context.setAutoCommit(false);
            for (MessageExt msg : msgs) {
                System.out.println("queueId:"+msg.getQueueId()+",orderId:"+new String(msg.getBody())+",i:"+msg.getKeys());
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
