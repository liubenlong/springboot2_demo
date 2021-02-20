package 设计模式.创建行.单例模式;

/**
 * @author albertliu
 * @className Singleton1
 * @description 饿汉
 * @date 2020/10/14 10:10
 */
public class Singleton1 {

    private Singleton1(){}

    private static final Singleton1 singleton = new Singleton1();

    public static final Singleton1 getInstance(){
        return singleton;
    }

}
