package 设计模式.创建行.单例模式;

/**
 * @author albertliu
 * @className Singleton
 * @description 类加载模式
 * @date 2020/10/14 10:08
 */
public class Singleton {

    private Singleton (){}

    private static final class SingletenHolder{
        private static final Singleton SINGLETON = new Singleton();
    }

    public static final Singleton getInstance(){
        return SingletenHolder.SINGLETON;
    }

}
