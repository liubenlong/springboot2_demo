package 设计模式.结构行.享元模式;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/14 10:59
 */
public class Main {
    public static void main(String [] args){
        ShapFactory factory = new ShapFactory();
        Shape shape = factory.createShap("red");
        shape.draw();

        shape = factory.createShap("blue");
        shape.draw();

        shape = factory.createShap("blue");
        shape.draw();

    }
}
