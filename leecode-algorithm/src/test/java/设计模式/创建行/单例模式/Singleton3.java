package 设计模式.创建行.单例模式;

/**
 * @author albertliu
 * @className Singleton1
 * @description 双重检查锁
 * @date 2020/10/14 10:10
 */
public class Singleton3 {

    private Singleton3(){}

    private static volatile Singleton3 singleton = null;

    public static final Singleton3 getInstance(){
        if(null == singleton){
            synchronized (Singleton3.class){
                if(null == singleton){
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }

}
