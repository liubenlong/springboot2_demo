package 设计模式.创建行.抽象工厂模式;

/**
 * @author albertliu
 * @className FactoryA
 * @description TODO
 * @date 2020/10/20 20:00
 */
public class FactoryA implements AbstractFactory{
    @Override
    public Shape getShape() {
        return new Square();
    }

    @Override
    public Color getColor() {
        return new Red();
    }
}
