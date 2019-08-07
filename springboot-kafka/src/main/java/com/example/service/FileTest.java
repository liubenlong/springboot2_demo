package com.example.service;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileTest {


    public static void main(String args[]) throws IOException {
        readFile();
//        writeFile();
    }

    /**
     * 读入TXT文件
     */
    public static void readFile() throws IOException {
        File[] files = new File("C:\\Users\\zhenhun\\Downloads\\sql").listFiles();
        Set<String> set = new HashSet<>();
        for (File file : files) {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                if (line.indexOf("(") == -1 && line.indexOf(")") == -1) {
                    String[] s = line.split(" ");
                    if("UNSIGNED_TINYINT".equalsIgnoreCase(s[s.length-1])) {
                        System.out.println(file.getName());
                    }

                    set.add(s[s.length-1]);
                }
            }
            br.close();
            reader.close();
        }
        System.out.println(set);

    }

    /**
     * 写入TXT文件
     */
    public static void writeFile() {
        try {
            File writeName = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("我会写入文件啦1\r\n"); // \r\n即为换行
                out.write("我会写入文件啦2\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
