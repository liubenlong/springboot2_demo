package 设计模式.创建行.抽象工厂模式;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/20 20:03
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory1 = new FactoryA();
        factory1.getShape().draw();

        AbstractFactory factory2 = new FactoryB();
        factory2.getShape().draw();
    }
}
