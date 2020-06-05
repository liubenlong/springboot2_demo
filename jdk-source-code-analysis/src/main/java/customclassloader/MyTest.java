package customclassloader;

import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) throws Exception {
        //这里取AppClassLoader的父加载器也就是ExtClassLoader作为MyClassLoaderCustom的classLoader
        MyClassLoaderCustom myClassLoaderCustom = new MyClassLoaderCustom(Thread.currentThread().getContextClassLoader().getParent());
        Class testAClass = myClassLoaderCustom.loadClass("customclassloader.TestA");
        Method mainMethod = testAClass.getDeclaredMethod("testCustomClassLoader");
        mainMethod.invoke(null);


    }
}