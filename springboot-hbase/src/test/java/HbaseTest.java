import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HbaseTest {

    @Test
    public void test1() throws IOException, InterruptedException {
        for (int i = 0; i < 5000; i++) {
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", "192.168.229.105");
            conf.set("hbase.zookeeper.peerport", "2181");
            conf.set("zookeeper.znode.parent", "/hbase");
            Connection connection = ConnectionFactory.createConnection(conf);


            TableName tableName = TableName.valueOf("t1");
            Table table = connection.getTable(tableName);

            List<Put> list = new LinkedList<>();
            for (int j = 0; j < 500; j++) {
                // 创建一个put请求，用于添加数据或者更新数据
                String s = UUID.randomUUID().toString();
//            Put put = new Put(Bytes.toBytes(s), 1567081811760L);
                Put put = new Put(Bytes.toBytes(s));
                put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("name"), Bytes.toBytes(s));
                put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("pass"), Bytes.toBytes(s));
                put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("address"), Bytes.toBytes(s));
                put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("email"), Bytes.toBytes(s));
                put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("dfsgtedf"), Bytes.toBytes(s));
                put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("dfg"), Bytes.toBytes(s));
                put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("sdf"), Bytes.toBytes(s));

                if (i % 2 == 0) {
                    put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("AGE"), Bytes.toBytes(i));
                }

                list.add(put);

            }
            Object[] results = new Object[list.size()];
            table.batch(list, results);

            table.close();
            connection.close();
        }


    }


    @Test
    public void test2() throws IOException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.229.105");
        conf.set("hbase.zookeeper.peerport", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        Connection connection = ConnectionFactory.createConnection(conf);


        TableName tableName = TableName.valueOf("PERSON");
        Table table = connection.getTable(tableName);

        // 创建一个扫描请求，查询多行数据
        Scan scan = new Scan();
        // 设置扫描器的缓存数量，遍历数据时不用发多次请求，默认100，适当的缓存可以提高性能
//        scan.setCaching(10);
        // 创建扫描结果，这个时候不会真正从HBase查询数据，下面的遍历才是去查询
        ResultScanner resultScanner = table.getScanner(scan);
        int i = 0;
        for (Result r : resultScanner) {
            String data = Bytes.toString(r.getValue(Bytes.toBytes("0"), Bytes.toBytes("name")));
            System.out.println(data);
            i++;
        }
        System.out.println(i);
        // 使用完毕关闭
        resultScanner.close();

        table.close();
        connection.close();


    }

    @Test
    public void test30() throws IOException, InterruptedException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.parse("2019-11-21 10:00:00").getTime());
    }


    @Test
    public void test3() throws IOException, InterruptedException, ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(format.parse("2019-11-19").getTime());
//        System.out.println(format.parse("2019-11-20").getTime());


String a = "ADM_LOAN_ALGO:SUANFA_FRAUD_APP_T_MATCH_FEATURES\n" +
        "loan_open:origin\n" +
        "loan_open:para_exception";

        for (String s : a.split("\\n")) {
//            System.out.println("snapshot '"+s+"'  , '"+s.replace(":",".")+"-1120-snapshot'");
//            System.out.println("./hbase org.apache.hadoop.hbase.snapshot.ExportSnapshot -snapshot \""+s.replace(":",".")+"-1120-snapshot'"+"\" -copy-from hdfs://10.1.168.18:9000/hbase -copy-to hdfs://10.1.168.10:9000/hbase -mappers 20 -bandwidth 20");
            System.out.println("clone_snapshot '"+s.replace(":",".")+"-1120-snapshot'"+"', '"+s+"'");
        }

    }


    @Test
    public void test4() throws IOException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.229.105");
        conf.set("hbase.zookeeper.peerport", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        Connection connection = ConnectionFactory.createConnection(conf);


        TableName tableName = TableName.valueOf("t1");
        Table table = connection.getTable(tableName);

        Delete delete = new Delete(Bytes.toBytes("p"));
        table.delete(delete);


        table.close();
        connection.close();
    }

    @Test
    public void test5() throws IOException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.229.101");
        conf.set("hbase.zookeeper.peerport", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        Connection connection = ConnectionFactory.createConnection(conf);

        List<Delete> deletes = new ArrayList<>();

        TableName tableName = TableName.valueOf("t1");
        Table table = connection.getTable(tableName);

        Delete delete = new Delete(Bytes.toBytes("ppp"));
        deletes.add(delete);

        Delete delete3 = new Delete(Bytes.toBytes("aaa"));
        delete3.addFamily(Bytes.toBytes("0"));
        deletes.add(delete3);

        table.delete(deletes);


        table.close();
        connection.close();
    }

    @Test
    public void test6() throws IOException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "172.16.49.96");
        conf.set("hbase.zookeeper.peerport", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        Connection connection = ConnectionFactory.createConnection(conf);


        TableName tableName = TableName.valueOf("DWS:EVT_SNSR_EVENT_LOAN_NEW_DI_1114");
        Table table = connection.getTable(tableName);

        Put put = new Put(Bytes.toBytes("a"));
        put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("PK"), Bytes.toBytes("aaa"));
        put.addColumn(Bytes.toBytes("0"), Bytes.toBytes("DISTINCT_ID"), Bytes.toBytes("aaa"));


        table.put(put);

        table.close();
        connection.close();
    }
}
