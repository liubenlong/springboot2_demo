import com.example.Application;
import com.example.config.HadoopConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@Slf4j
public class HadoopTest {


    private FileSystem fs;
    @Autowired
    private HadoopConfig hadoopConfig;

    @Before
    public void initFS() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        fs = FileSystem.get(new URI(hadoopConfig.getNameNode()), conf, "appweb");
    }

    @After
    public void closeFS() throws IOException {
        fs.close();
    }

    /**
     * 判断是否存在目录
     * 创建目录
     */
    @Test
    public void existDir() {
        System.out.println(existDir(hadoopConfig.getNameSpace(), true));
    }

    @Test
    public void uploadFile() {
        copyFileToHDFS("c://2、Netty宏观理解.rar", hadoopConfig.getNameSpace());
    }

    /**
     * 写入文件
     */
    @Test
    public void writeData() throws IOException {
        FSDataOutputStream outputStream = null;
        try {
            String filePath = "/zhenhun/server.log";
            outputStream = fs.create(new Path(filePath));

            byte[] buff = "hello world!".getBytes();
            outputStream.write(buff, 0, buff.length);
            outputStream.close();
        } finally {
            if (null != outputStream) {
                outputStream.close();
            }
        }
    }

    /**
     * 写入文件
     */
    @Test
    public void writeData2() throws IOException {
        FSDataOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            String filePath = "/zhenhun/a.log";
            outputStream = fs.create(new Path(filePath));
            fileInputStream = new FileInputStream(new File("d://a.log"));
            IOUtils.copyBytes(fileInputStream, outputStream, new Configuration());
        } finally {
            if (null != outputStream) {
                outputStream.close();
            }
            if (null != fileInputStream) {
                fileInputStream.close();
            }

        }
    }


    /**
     * 追加
     * @throws IOException
     */
    @Test
    public void append() throws IOException {
        //要追加的文件流，inpath为文件
        InputStream in = new BufferedInputStream(new FileInputStream("d://b.log"));
        OutputStream out = fs.append(new Path("/zhenhun/a.log"));

        IOUtils.copyBytes(in, out, 4096, true);

        out.close();
        in.close();
    }


    @Test
    public void readData() throws IOException {
        FSDataInputStream inputStream = null;
        try {
            Path file = new Path("/zhenhun/a.log");
            inputStream = fs.open(file);
            FileStatus stat = fs.getFileStatus(file); // 读取文件状态
            byte[] buf = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
            inputStream.readFully(0, buf);
            System.out.println(new String(buf));
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
    }


    @Test
    public void readData2() throws IOException {
        FSDataInputStream inputStream = null;
        try {
            inputStream = fs.open(new Path("/zhenhun/a.log"));
            IOUtils.copyBytes(inputStream, System.out, 4096);
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
    }

    /**
     * 按行读取文件内容
     *
     * @throws IOException
     */
    @Test
    public void readData3() throws IOException {
        Path file = new Path("/zhenhun/a.log");
        FSDataInputStream is = fs.open(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
        is.close();
        fs.close();
    }

    /**
     * 文件遍历
     */
    @Test
    public void liststatus() throws Exception {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/zhenhun"));
        for (FileStatus fs : fileStatuses) {
            System.out.println(fs.isDirectory() ? (fs.getPath().getName() + " is directory") : (fs.getPath().getName() + " is file"));
        }
    }


    /**
     * 创建目录
     *
     * @param filePath
     * @param create
     * @return
     */
    private boolean existDir(String filePath, boolean create) {
        boolean flag = false;
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath不能为空");
        }
        try {
            Path path = new Path(filePath);
            if (create) {
                if (!fs.exists(path)) {
                    fs.mkdirs(path);
                }
            }
            if (fs.isDirectory(path)) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return flag;
    }

    /**
     * 文件上传至 HDFS
     *
     * @param srcFile  源文件，上传文件路径
     * @param destPath hdfs的目的路径
     */
    private void copyFileToHDFS(String srcFile, String destPath) {
        // 源文件路径是Linux下的路径，如果在 windows 下测试，需要改写为Windows下的路径，比如D://hadoop/djt/weibo.txt
        Path srcPath = new Path(srcFile);

        // 目的路径
        if (StringUtils.isNotBlank(hadoopConfig.getNameNode())) {
            destPath = hadoopConfig.getNameNode() + destPath;
        }
        Path dstPath = new Path(destPath);
        try {
            fs.copyFromLocalFile(srcPath, dstPath);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public void download() {
        getFile(hadoopConfig.getNameSpace() + "/2、Netty宏观理解.rar", "c://");
    }


    /**
     * 删除文件或者文件目录
     *
     * @param path
     */
    private void rmdir(String path, String fileName) {
        try {
            // 返回FileSystem对象
            if (StringUtils.isNotBlank(hadoopConfig.getNameNode())) {
                path = hadoopConfig.getNameNode() + path;
            }
            if (StringUtils.isNotBlank(fileName)) {
                path = path + "/" + fileName;
            }
            // 删除文件或者文件目录  delete(Path f) 此方法已经弃用
            fs.delete(new Path(path), true);
        } catch (IllegalArgumentException | IOException e) {
            log.error("", e);
        }
    }

    /**
     * 从 HDFS 下载文件
     *
     * @param hdfsFile
     * @param destPath 文件下载后,存放地址
     */
    private void getFile(String hdfsFile, String destPath) {
        // 源文件路径
        if (StringUtils.isNotBlank(hadoopConfig.getNameNode())) {
            hdfsFile = hadoopConfig.getNameNode() + hdfsFile;
        }
        Path hdfsPath = new Path(hdfsFile);
        Path dstPath = new Path(destPath);
        try {
            // 下载hdfs上的文件
            fs.copyToLocalFile(hdfsPath, dstPath);
            // 释放资源
            // fs.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
