package 自定义类加载器;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * 自定义ClassLoader
 */
public class MyClassLoader extends ClassLoader {

    protected Class<?> findClass(String path, String name) {
        try {
            FileInputStream in = new FileInputStream(path + name + ".class");
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