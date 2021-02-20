package 设计模式.结构行.外观模式;

/**
 * @author albertliu
 * @className Square
 * @description 正方形
 * @date 2020/10/14 10:41
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Square");
    }
}
