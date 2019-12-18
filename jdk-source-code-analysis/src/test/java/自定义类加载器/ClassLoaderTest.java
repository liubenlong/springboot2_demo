package 自定义类加载器;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderTest {


    /**
     * URLClassLoader
     * 推荐此方法
     */
    @Test
    public void test0() {
        try {
            URLClassLoader diskLoader = new URLClassLoader(new URL[]{new URL("file:/D:/liubenlong/a/")});//最后面的斜杠需要添加
            URLClassLoader diskLoader1 = new URLClassLoader(new URL[]{new URL("file:/D:/liubenlong/b/")});

            //加载class文件
            Class clz = diskLoader.loadClass("Hello");
            Constructor constructor = clz.getConstructor(String.class);
            Object obj = constructor.newInstance("tom");

            /**
             * 类Hello引用了类Dog，类加载器会主动加载被引用的类。
             * 注意一般是我们使用 URLClassLoader 实现自定义的类加载器。如果使用classLoader，则需要重写findClass方法来实现类字节码的加载
             */
            Method method = clz.getMethod("sayHello", null);
            //通过反射调用Test类的say方法
            method.invoke(obj, null);



            Class clz1 = diskLoader1.loadClass("Hello");
            Constructor constructor1 = clz1.getConstructor(String.class);
            Object obj1 = constructor1.newInstance("cat");

            Method method1 = clz1.getMethod("sayHello", null);
            //通过反射调用Test类的say方法
            method1.invoke(obj1, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 集成ClassLoader，重写findClass实现自定义类加载器
     */
    @Test
    public void test1() {
        try {
            MyClassLoader diskLoader = new MyClassLoader();
            MyClassLoader diskLoader1 = new MyClassLoader();

            //依赖的类需要提前加载，不是应该自动加载的吗？  应该使用 URLClassLoader 指定路径
            diskLoader.findClass("D:\\liubenlong\\a\\", "Dog");
            diskLoader1.findClass("D:\\liubenlong\\b\\", "Dog");

            //加载class文件
            Class clz = diskLoader.findClass("D:\\liubenlong\\a\\", "Hello");

            Constructor constructor = clz.getConstructor(String.class);
            Object obj = constructor.newInstance("tom");

            Method method = clz.getMethod("sayHello", null);
            //通过反射调用Test类的say方法
            method.invoke(obj, null);




            Class clz1 = diskLoader1.findClass("D:\\liubenlong\\b\\", "Hello");

            Constructor constructor1 = clz1.getConstructor(String.class);
            Object obj1 = constructor1.newInstance("cat");

            Method method1 = clz1.getMethod("sayHello", null);
            //通过反射调用Test类的say方法
            method1.invoke(obj1, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     */
    @Test
    public void test2() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        URLClassLoader diskLoader = new URLClassLoader(new URL[]{new URL("file:/D:/liubenlong/b/")});//最后面的斜杠需要添加
        Class clz = diskLoader.loadClass("java.lang.String");
        Constructor constructor = clz.getConstructor(String.class);
        Object obj = constructor.newInstance("tom");

        /**
         * 类Hello引用了类Dog，类加载器会主动加载被引用的类。
         * 注意一般是我们使用 URLClassLoader 实现自定义的类加载器。如果使用classLoader，则需要重写findClass方法来实现类字节码的加载
         */
        Method method = clz.getMethod("sayHello", null);
        //通过反射调用Test类的say方法
        method.invoke(obj, null);
    }


    @Test
    public void test3() {
        try {
            MyClassLoader11 diskLoader = new MyClassLoader11();

            //加载class文件
            Class clz = diskLoader.loadClass("java.lang.String");

            Constructor constructor = clz.getConstructor(String.class);
            Object obj = constructor.newInstance("tom");

            Method method = clz.getMethod("sayHello", null);
            //通过反射调用Test类的say方法
            method.invoke(obj, null);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
