package 设计模式.结构行.外观模式;

/**
 * @author albertliu
 * @className Rectangle
 * @description 长方形
 * @date 2020/10/14 10:42
 */
public class Rectangle  implements Shape{
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}
