package 设计模式.结构行.适配器模式.类适配器;

/**
 * @author albertliu
 * @className Adapter
 * @description TODO
 * @date 2020/10/20 20:51
 */
public class Adapter extends B implements A{
    @Override
    public void say() {
        System.out.println("适配器");
        super.hello();
    }
}
