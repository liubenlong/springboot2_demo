package 自定义类加载器;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义ClassLoader
 */
public class MyClassLoader11 extends ClassLoader {

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            FileInputStream in = new FileInputStream("D:/liubenlong/b/java/lang/String.class");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            in.close();
            byte[] classBytes = baos.toByteArray();
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}