import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

@Slf4j
public class HbaseSink implements SinkFunction<String> {
    @Override
    public void invoke(String value, Context context) throws Exception {
        Connection connection = null;
        Table table = null;
        try {
            // 加载HBase的配置
            Configuration configuration = HBaseConfiguration.create();

            // 读取配置文件
            configuration.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
            configuration.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));
            connection = ConnectionFactory.createConnection(configuration);

            TableName tableName = TableName.valueOf("test");

            // 获取表对象
            table = connection.getTable(tableName);

            //row1:cf:a:aaa
            String[] split = value.split(":");

            // 创建一个put请求，用于添加数据或者更新数据
            Put put = new Put(Bytes.toBytes(split[0]));
            put.addColumn(Bytes.toBytes(split[1]), Bytes.toBytes(split[2]), Bytes.toBytes(split[3]));
            table.put(put);
            log.error("[HbaseSink] : put value:{} to hbase", value);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (null != table) table.close();
            if (null != connection) connection.close();
        }
    }

}
