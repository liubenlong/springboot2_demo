package 设计模式.结构行.代理模式.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author albertliu
 * @className MyProxy
 * @description TODO
 * @date 2020/10/20 20:29
 */
public class MyProxy implements InvocationHandler {

    private IImage iImage;

    public MyProxy(IImage iImage){
        this.iImage=iImage;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy...");
        return method.invoke(iImage, args);
    }
}
