import com.alibaba.fastjson.JSON;
import org.apache.phoenix.jdbc.PhoenixDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Phoenix113Test {

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
            PhoenixDriver phoenixDriver = new PhoenixDriver();
            conn = phoenixDriver.connect("jdbc:phoenix:172.16.48.191", props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create() {
        try {
            String createSql = "CREATE TABLE user (id varchar PRIMARY KEY,name varchar ,passwd varchar)";
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
            String upsertSql = "upsert into USER (id,name, PASSWD) values ('8','zhangsan' ,'10')";
//            String[] param = {"2", "李四", "22", "1"};
            PreparedStatement ps = conn.prepareStatement(upsertSql);
//            for (int i = 1; i <= param.length; i++) {
//                ps.setString(i, param[i - 1]);
//            }
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
            String sql = "select * from RAW.CELL_BASIC limit 1";
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