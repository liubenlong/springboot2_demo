package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_TopicTest2")
public class MyConsumer1 implements RocketMQListener<String> {
    public void onMessage(String message) {
        log.info("received message: " + message);
    }
}
