package 设计模式.行为行.模板模式;

/**
 * @author albertliu
 * @className BascketBall
 * @description TODO
 * @date 2020/10/20 15:09
 */
public class BascketBall extends AbstractGame{

    @Override
    public void playBasketball() {
        System.out.println("aaaaa   playBasketball");
    }

    @Override
    public void playFootball() {
        System.out.println("aaaaa   playFootball");
    }
}
