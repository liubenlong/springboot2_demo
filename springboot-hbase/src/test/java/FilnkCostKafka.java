import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

@Slf4j
public class FilnkCostKafka {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(1000);

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "node1:9092");

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(
                "my-test-topic",
                new SimpleStringSchema(),
                properties);

        //从最早开始消费
        consumer.setStartFromEarliest();
        DataStream<String> stream = env.addSource(consumer);
//        stream.print();

        stream.process(new HbaseSink());

        env.execute("kafka->flink->hbase");
    }

}