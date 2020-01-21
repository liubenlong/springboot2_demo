package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @GetMapping("/hello")
    public String hello() {
        // 如下两种方式等价
        rocketMQTemplate.convertAndSend("TopicTest", "Hello, World!");
        rocketMQTemplate.send("TopicTest", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());


//        // 第三个参数为key
//        rocketMQTemplate.syncSend("TopicTest", "Hello, World! I'm from simple message", "18122811143034568830");
//
//        // topic: ORDER，tag: paid, cacel
//        rocketMQTemplate.convertAndSend("ORDER:paid", "Hello, World!");
//        rocketMQTemplate.convertAndSend("ORDER:cancel", "Hello, World!");
//
//        // 消息体为自定义对象
//        rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
//
//
//        // 发送延迟消息
//        rocketMQTemplate.sendDelayed("TopicTest", "I'm delayed message", MessageDelayLevel.TIME_1M);
//
//        // 发送即发即失消息（不关心发送结果）
//        rocketMQTemplate.sendOneWay("TopicTest", MessageBuilder.withPayload("I'm one way message").build());
//
//
//        // 发送顺序消息
//        rocketMQTemplate.syncSendOrderly("test-topic-4", "I'm order message", "1234");
//
//        // 发送异步消息
//        rocketMQTemplate.asyncSend("TopicTest", MessageBuilder.withPayload("I'm one way message").build(), new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//
//            }
//
//            @Override
//            public void onException(Throwable e) {
//
//            }
//        });
//
//        System.out.println("send finished!");


        return "Welcome to springboot2 world ~";
    }


}