package customclassloader;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyClassLoaderCustom extends ClassLoader {

    private ClassLoader classLoader;

    private Map<String, String> classPathMap = new HashMap<>();

    public MyClassLoaderCustom(ClassLoader classLoader) {
        this.classLoader = classLoader;
        classPathMap.put("customclassloader.TestA", "D:\\IdeaProjects\\springboot2_demo\\jdk-source-code-analysis\\target\\classes\\customclassloader\\TestA.class");
        classPathMap.put("customclassloader.TestB", "D:\\IdeaProjects\\springboot2_demo\\jdk-source-code-analysis\\target\\classes\\customclassloader\\TestB.class");
    }

    @SneakyThrows
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = null;
        try {
            //这里要使用 JDK 的类加载器加载 java.lang 包里面的类
            result = classLoader.loadClass(name);
        } catch (Exception e) {}
        if (result != null) {
            return result;
        }
        String classPath = classPathMap.get(name);
        File file = new File(classPath);
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] classBytes = getClassData(file);
        if (classBytes == null || classBytes.length == 0) {
            throw new ClassNotFoundException();
        }
        return defineClass(classBytes, 0, classBytes.length);
    }


    private byte[] getClassData(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        int iAvail = is.available();
        byte[] bytes = new byte[iAvail];
        is.read(bytes);
        is.close();
        return bytes;
    }
}