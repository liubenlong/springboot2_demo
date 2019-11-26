package com.example.service;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;
import org.apache.flink.util.Collector;
import scala.Tuple2;

import java.util.Properties;

public class Test1 {

    public static void main(String[] args) throws Exception {
        final org.apache.flink.streaming.api.environment.StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(1000);

        //设置kafka连接参数
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "172.16.52.4:9092,172.16.52.5:9092,172.16.52.6:9092");
//        properties.setProperty("zookeeper.connect", "ip0:2181");
        properties.setProperty("group.id", "test1");

        FlinkKafkaConsumerBase<String> consumer = new FlinkKafkaConsumer011<>(
                "stanlee.dataservice.hbase113m.to.hbase215.hbase", new SimpleStringSchema(), properties);
        //从最早开始消费
        consumer.setStartFromEarliest();
        consumer.setStartFromEarliest();
        DataStream<String> stream = env.addSource(consumer);
        stream.print();
        //stream.map();
        env.execute();

    }


    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        private static final long serialVersionUID = 1L;

        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }

}
