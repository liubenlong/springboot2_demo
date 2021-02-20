package 设计模式.结构行.享元模式;

/**
 * @author albertliu
 * @className Circle
 * @description TODO
 * @date 2020/10/14 10:52
 */
public class Circle implements Shape {
    private String color;//颜色

    public Circle(String color) {
        System.out.println("----------create");
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("当前画的圆：color=" + color);
    }
}
