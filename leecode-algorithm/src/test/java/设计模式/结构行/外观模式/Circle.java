package 设计模式.结构行.外观模式;

/**
 * @author albertliu
 * @className Circle
 * @description TODO
 * @date 2020/10/14 10:40
 */
public class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("circle");
    }
}
