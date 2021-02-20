package 设计模式.行为行.状态模式;

/**
 * @author albertliu
 * @className Main
 * @description 状态模式
 * @date 2020/10/14 11:37
 */
public class Main {
    public static void main(String[] args) {
        ScoreContext context = new ScoreContext();

        context.setScore(20);
        context.setScore(70);
        System.out.println("=======" + context.getState().getScore());
        context.setScore(90);
        context.setScore(20);

    }
}
