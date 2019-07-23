import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
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
public class HbaseSink extends ProcessFunction<String, String> {
    private static final long serialVersionUID = 1L;

    private Connection connection = null;
    private Table table = null;

    @Override
    public void open(org.apache.flink.configuration.Configuration parameters) throws Exception {
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

            log.info("[HbaseSink] : open HbaseSink finished");
        } catch (Exception e) {
            log.error("[HbaseSink] : open HbaseSink faild {}", e);
        }
    }

    @Override
    public void close() throws Exception {
        log.info("close...");
        if (null != table) table.close();
        if (null != connection) connection.close();
    }

    @Override
    public void processElement(String value, Context ctx, Collector<String> out) throws Exception {
        try {
            log.info("[HbaseSink] value={}", value);

            //row1:cf:a:aaa
            String[] split = value.split(":");

            // 创建一个put请求，用于添加数据或者更新数据
            Put put = new Put(Bytes.toBytes(split[0]));
            put.addColumn(Bytes.toBytes(split[1]), Bytes.toBytes(split[2]), Bytes.toBytes(split[3]));
            table.put(put);
            log.error("[HbaseSink] : put value:{} to hbase", value);
        }catch (Exception e){
            log.error("", e);
        }

    }


}