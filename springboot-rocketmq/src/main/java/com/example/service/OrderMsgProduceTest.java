package com.example.service;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

public class OrderMsgProduceTest {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
        producer.setNamesrvAddr("node1:9876");
        //Launch the instance.
        producer.start();

        //顺序发送100条编号为0到99的，orderId为1 的消息
        new Thread(() -> {
            Integer orderId = 1;
            sendMessage(producer, orderId);
        }).start();
        //顺序发送100条编号为0到99的，orderId为2 的消息
        new Thread(() -> {
            Integer orderId = 2;
            sendMessage(producer, orderId);
        }).start();

        TimeUnit.SECONDS.sleep(5);

        producer.shutdown();
    }

    private static void sendMessage(MQProducer producer, Integer orderId) {
        for (int i = 0; i < 30; i++) {
            try {
                Message msg =
                        new Message("TopicTest", "TagA", i + "",
                                (orderId + "").getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg, (mqs, msg1, arg) -> {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }, orderId);
                System.out.println("message send,orderId:" + orderId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
