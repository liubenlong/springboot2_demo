import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
public class MyCommonTest  {


    @Test
    public void test2() throws URISyntaxException, IOException {
        // 加载HBase的配置
        Configuration configuration = HBaseConfiguration.create();

        // 读取配置文件
        configuration.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        configuration.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));
        Connection connection = null;
        try {
            // 创建一个HBase连接
            connection = ConnectionFactory.createConnection(configuration);

            TableName tableName = TableName.valueOf("test");

            // 获取表对象
            Table table = connection.getTable(tableName);

            // 创建一个查询请求，查询一行数据
            Get get = new Get(Bytes.toBytes("row1"));
            // 由于HBase的一行可能非常大，所以限定要取出的列族
            get.addFamily(Bytes.toBytes("cf"));
            // 创建一个结果请求
            Result result = table.get(get);
            // 从查询结果中取出name列，然后打印（这里默认取最新版本的值，如果要取其他版本要使用Cell对象）
            byte[] name = result.getValue(Bytes.toBytes("cf"), Bytes.toBytes("age"));
            System.out.println(Bytes.toString(name));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) connection.close();
        }
    }
}