import org.junit.Test;

import java.io.*;

public class aa {
    private static final String INSERT_TABLES_SQL = "update ds_tables set status = 3 where schema_name = %s and table_name=%s;\n";


    public static void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] split = line.split("\\.");
            if (split.length == 1) {
                line = String.format(INSERT_TABLES_SQL, null, "'" + split[0] + "'");
            } else {
                line = String.format(INSERT_TABLES_SQL, "'" + split[0] + "'", "'" + split[1] + "'");
            }
            writeFile(new File("d:\\sql.txt"), line);
        }
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
        readFile(new File("d:\\1.txt"));
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

}
