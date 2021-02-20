package 设计模式.创建行.单例模式;

/**
 * @author albertliu
 * @className Singleton1
 * @description 懒汉
 * @date 2020/10/14 10:10
 */
public class Singleton2 {

    private Singleton2(){}

    private static Singleton2 singleton = null;

    public static  final synchronized Singleton2 getInstance(){
        if(singleton == null){
            singleton = new Singleton2();
        }
        return singleton;
    }



}
