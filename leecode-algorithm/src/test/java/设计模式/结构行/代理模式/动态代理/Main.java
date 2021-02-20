package 设计模式.结构行.代理模式.动态代理;

import java.lang.reflect.Proxy;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/20 16:33
 */
public class Main {
    public static void main(String[] args) {
        IImage image = new Image();
        MyProxy myProxy = new MyProxy(image);

        IImage imageProxy = (IImage) Proxy.newProxyInstance(IImage.class.getClassLoader(), new Class[]{IImage.class}, myProxy);
        imageProxy.show();
    }
}
