import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class aa {
    private static final String INSERT_TABLES_SQL = "update ds_tables set status = 3 where schema_name = %s and table_name=%s;\n";


    public static Map<String, String> map = new HashMap<>();
    public static List<String> list = new ArrayList<>();

    public static void readFile(File file) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String line = null;
        List<String> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        br.close();


        String a = "";

        String[] split = a.split("\\n");
        for (String s : split) {
            list.remove(s);
        }

        list.forEach(s -> {
            try {
                writeFile(new File("d:\\cccc.txt"), s + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public static void writeFile(File file, String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(content);
        osw.flush();
    }

    public static void appendFile(File file, String content) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(
                new FileOutputStream(file, true), // true to append
                "UTF-8"
        );
        out.write(content);
        out.close();
    }

    @Test
    public void aaaaaaaa() throws IOException {
        readFile(new File("d:\\11111.txt"));
//        System.out.println("default\tcreditcard_jedi\t8\t0\t0\t7\t0\t'creditcard_jedi', {NAME => 'cf', COMPRESSION => 'SNAPPY'}\n"
//                .split("\t")[1]);
    }

    @Test
    public void bbbbbbbbb() throws IOException {
        String sql = "select * from ds_tables  where table_name='%s' and schema_name = '%s';";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:\\1.txt")), "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] split = line.split("\\.");
            if (split.length == 1) {
                writeFile(new File("d:\\tablename.txt"), String.format("select * from ds_tables  where table_name='%s' and schema_name is null;", split[0]) + "\n");
            } else {
                writeFile(new File("d:\\tablename.txt"), String.format(sql, split[1], split[0]) + "\n");
            }
        }
    }


    @Test
    public void cccc() throws IOException {

        String a = "bee_ads_impression\n" +
                "bee_user_action\n" +
                "creditcard-creditscore\n" +
                "creditcard-creditscore-customize\n" +
                "creditcard-invoke\n" +
                "creditcard-message\n" +
                "creditcard-tracer\n" +
                "creditcard_ebank\n" +
                "creditcard_jedi\n" +
                "creditcard_user_portrait\n" +
                "loan_black_hawk\n" +
                "loan_keywords\n" +
                "loan_normandy:feedback_record\n" +
                "loan_normandy:origin\n" +
                "loan_normandy:para_exception\n" +
                "loan_open:feedback_record\n" +
                "loan_open:origin\n" +
                "loan_open:para_exception\n" +
                "model:sougo_phone_lib\n" +
                "variable_center:variables\n";

        String[] split = a.split("\\n");
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split("\\:");
            if (split1.length == 2) {
                System.out.println("insert into tb_table_info(full_table_name,schema_name,table_name,table_type,read_status,write_status,read_cluster_id,write_cluster_id) " +
                        "values('" + split[i] + "','" + split1[0] + "','" + split1[1] + "',2,1,1,'master','master');");
            } else {
                System.out.println("insert into tb_table_info(full_table_name,schema_name,table_name,table_type,read_status,write_status,read_cluster_id,write_cluster_id) " +
                        "values('" + split[i] + "','default','" + split1[0] + "',2,1,1,'master','master');");
            }
        }

    }

}
