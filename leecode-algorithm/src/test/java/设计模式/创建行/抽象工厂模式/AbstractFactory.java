package 设计模式.创建行.抽象工厂模式;

/**
 * @author albertliu
 * @className AbstractFactory
 * @description TODO
 * @date 2020/10/20 19:59
 */
public interface AbstractFactory {
    Shape getShape();
    Color getColor();
}
