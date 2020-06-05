package customclassloader;

import java.lang.reflect.Method;

//参考：https://zhuanlan.zhihu.com/p/141527120
public class MyTest {

    public static void main(String[] args) throws Exception {
        //这里取AppClassLoader的父加载器也就是ExtClassLoader作为MyClassLoaderCustom的classLoader
        MyClassLoaderCustom myClassLoaderCustom = new MyClassLoaderCustom(Thread.currentThread().getContextClassLoader().getParent());
        Class testAClass = myClassLoaderCustom.loadClass("customclassloader.TestA");
        Method mainMethod = testAClass.getDeclaredMethod("testCustomClassLoader");
        mainMethod.invoke(null);


    }
}