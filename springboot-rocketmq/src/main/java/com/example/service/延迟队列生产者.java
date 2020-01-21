package com.example.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

@Slf4j
public class 延迟队列生产者 {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
        producer.setNamesrvAddr("node1:9876");
        //Launch the instance.
        producer.start();

        Message message = new Message("TopicTest", ("this is a delay message:" + "hhhhhhhhhh").getBytes());

        //"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
        message.setDelayTimeLevel(3);//3指上面第三个策略，是10秒。
        producer.send(message, new SendCallback() {

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


        TimeUnit.SECONDS.sleep(3);

        producer.shutdown();
    }
}
