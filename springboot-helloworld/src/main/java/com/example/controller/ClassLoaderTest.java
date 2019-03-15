package com.example.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

//        自定义类加载器加载Test类。注意：Test需要放在项目目录之外，否则会走委托，不会使用到我们自定义的类加载器哦
        MyClassLoader classLoader = new MyClassLoader("D:");
        Class clazz = classLoader.loadClass("com.example.controller.Test");
        Object obj = clazz.newInstance();
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);
        System.out.println(clazz.getClassLoader().toString());



        //自定义类加载器的加载路径
        MyClassLoader myClassLoader = new MyClassLoader("D:");
        //包名+类名
        Class c = myClassLoader.loadClass("java.lang.String");

        if (c != null) {
            obj = c.newInstance();
            Method method = c.getMethod("equals", Object.class);
            method.invoke(obj, "a'");
            System.out.println(c.getClassLoader().toString());//这里会报空指针，因为他是由跟加载器加载，跟加载器是C++写的，java中看不到，所以扩展类加载器的附加载器结果是null
            //https://blog.csdn.net/briblue/article/details/54973413
        }


    }

}