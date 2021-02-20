package 设计模式.结构行.适配器模式.类适配器;

/**
 * @author albertliu
 * @className Main
 * @description A->adapter->B
 * @date 2020/10/20 20:52
 */
public class Main {
    public static void main(String[] args){
        Adapter adapter = new Adapter();
        adapter.say();
    }
}
