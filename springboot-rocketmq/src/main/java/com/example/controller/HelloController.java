package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        // 如下两种方式等价
        rocketMQTemplate.convertAndSend("TopicTest", "Hello, World!");
        rocketMQTemplate.send("TopicTest", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        rocketMQTemplate.syncSend("TopicTest", "Hello, World! I'm from simple message");

        // 消息体为自定义对象 todo BigDecimal传输精度问题??
        rocketMQTemplate.convertAndSend("test-topic-2", Stu.builder().age(10).id("1").money(new BigDecimal(10.22)).name("tom").build());

        // 发送即发即失消息（不关心发送结果）
        rocketMQTemplate.sendOneWay("TopicTest", MessageBuilder.withPayload("发送即发即失消息（不关心发送结果）").build());


        // 发送异步消息
        rocketMQTemplate.asyncSend("TopicTest", MessageBuilder.withPayload("发送异步消息").build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info(sendResult.toString());
            }

            @Override
            public void onException(Throwable e) {
                log.error(e.getMessage(), e);
            }
        });

        System.out.println("send finished!");
        TimeUnit.SECONDS.sleep(1);


        return "Welcome to springboot2 world ~";
    }


    @GetMapping("/hello1")
    public String hello1() {
        // topic: ORDER，tag: paid, cacel  TODO 如何按tag消费
        rocketMQTemplate.convertAndSend("TopicTest:paid", "Hello, World!--paid");
        rocketMQTemplate.convertAndSend("TopicTest:cancel", "Hello, World!--cancel");
        return null;
    }


    @GetMapping("/hello2")
    public String hello2() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        // 发送延迟消息  TODO
        int totalMessagesToSend = 10;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
//            String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
            //3对应10秒后发送消息
            message.setDelayTimeLevel(3);
            // Send the message
            rocketMQTemplate.getProducer().send(message, new SendCallback() {

                //消息发送成功回调
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info(sendResult.toString());
                }
                //消息异常回调
                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    //补偿机制，根据业务情况进行使用，看是否进行重试
                }
            });
        }

        return null;
    }


    /**
     * 发送顺序消息.. 有点问题，还是使用原生API吧
     *
     * @return
     * @throws UnsupportedEncodingException
     * @throws InterruptedException
     */
    @GetMapping("/hello3")
    public String hello3() throws UnsupportedEncodingException, InterruptedException {
//        rocketMQTemplate.syncSendOrderly("test-topic-4", "I'm order message", "1234");
        return null;
    }


}