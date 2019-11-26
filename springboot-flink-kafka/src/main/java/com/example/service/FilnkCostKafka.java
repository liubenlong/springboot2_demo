package com.example.service;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.util.Collector;
import scala.Tuple2;

import java.util.Properties;

public class FilnkCostKafka {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(1000);

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "172.16.52.4:9092,172.16.52.5:9092,172.16.52.6:9092");
//        properties.setProperty("zookeeper.connect", "172.16.52.4:2181,172.16.52.5:2181,172.16.52.6:2181");
        properties.setProperty("group.id", "test");

        FlinkKafkaConsumer09<String> myConsumer = new FlinkKafkaConsumer09<>("stanlee.dataservice.hbase113m.to.hbase215.hbase",
                new SimpleStringSchema(), properties);

        DataStream<String> stream = env.addSource(myConsumer);
        stream.flatMap(new LineSplitter());

        env.execute("WordCount from Kafka data");
    }

    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        private static final long serialVersionUID = 1L;

        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {

            System.out.println(value);

            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<>(token, 1));
                }
            }
        }
    }
}