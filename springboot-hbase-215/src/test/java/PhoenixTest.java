import com.alibaba.fastjson.JSON;
import org.apache.phoenix.query.QueryServices;
import org.apache.phoenix.util.PhoenixRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class PhoenixTest {

    static Connection conn = null;
    static ResultSet rs = null;

    /**
     * 创建一个新的链接不是一个昂贵的操作，所以这里就不使用连接池了。
     * @return
     */
    @Before
    public void getConnection() {
        try {
            Properties props = new Properties();
            props.put(QueryServices.DEFAULT_COLUMN_ENCODED_BYTES_ATRRIB, "0");
            props.setProperty(PhoenixRuntime.CURRENT_SCN_ATTRIB, Long.toString(System.currentTimeMillis()));
            props.setProperty(QueryServices.IS_NAMESPACE_MAPPING_ENABLED, "true");//命名空间映射
            props.setProperty(QueryServices.IS_SYSTEM_TABLE_MAPPED_TO_NAMESPACE, "true");
            conn = DriverManager.getConnection("jdbc:phoenix:172.16.50.41,172.16.50.42,172.16.50.43:2181:/hbase;RequestMetric=true", props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create() {
        try {
            String createSql = "CREATE TABLE user (pk varchar PRIMARY KEY,name varchar ,passwd varchar)";
            PreparedStatement ps = conn.prepareStatement(createSql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void  upsert() {
        try {
            String upsertSql = "upsert into ZEUS.PERSON77 (pk, age, name) values ('1',1,'d')";
            PreparedStatement ps = conn.prepareStatement(upsertSql);
            ps.executeUpdate();
            conn.commit(); // you must commit
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  delete() {
        try {
            String upsertSql = "delete from ZEUS.PERSON77 where pk='1'";
            PreparedStatement ps = conn.prepareStatement(upsertSql);
            ps.executeUpdate();
            conn.commit(); // you must commit
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void query() {
        try {
            String sql = "select * from ZEUS.PERSON77";
            String[] param = null;

            PreparedStatement ps = conn.prepareStatement(sql);
            if (param != null) {
                for (int i = 1; i <= param.length; i++) {
                    ps.setString(i, param[i - 1]);
                }
            }

            rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colLength = meta.getColumnCount();
            List<String> colName = new ArrayList<>();
            for (int i = 1; i <= colLength; i++) {
                colName.add(meta.getColumnName(i));
            }

            List<String[]> result = new ArrayList<>();
            String[] colArr;
            while (rs.next()) {
                colArr = new String[colLength];
                for (int i = 0; i < colLength; i++) {
                    colArr[i] = rs.getString(colName.get(i));
                }
                result.add(colArr);
            }
            ps.close();
            System.out.println(JSON.toJSONString(result));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After
    public void close(){
        System.out.println("close........");
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}