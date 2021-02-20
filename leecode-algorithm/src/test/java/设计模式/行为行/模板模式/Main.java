package 设计模式.行为行.模板模式;

/**
 * @author albertliu
 * @className Main
 * @description 模板方法模式。本质是面向接口编程，开闭原则
 * @date 2020/10/14 10:45
 */
public class Main {

    public static void main(String[] args) {
        IGame game = new BascketBall();
        game.play();

        IGame game1 = new FootBall();
        game1.play();
    }

}
