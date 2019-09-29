import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

public class HbaseTest {

    @Test
    public void test1() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "172.16.48.191,172.16.48.192,172.16.48.193");
        conf.set("hbase.zookeeper.peerport", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        Connection connection = ConnectionFactory.createConnection(conf);


        TableName tableName = TableName.valueOf("test");
        Table table = connection.getTable(tableName);

        // 创建一个put请求，用于添加数据或者更新数据
        Put put = new Put(Bytes.toBytes("row1"), 1567081811760L);
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("yyy"), Bytes.toBytes("sdsds"));
        table.put(put);

    }
}
