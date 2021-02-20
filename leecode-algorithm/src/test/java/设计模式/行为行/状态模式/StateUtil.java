package 设计模式.行为行.状态模式;

/**
 * @author albertliu
 * @className StateUtil
 * @description TODO
 * @date 2020/10/14 16:29
 */
public class StateUtil {
    public static void modifyState(int score, ScoreContext context) {
        if (score >= 60 && score < 80) {
            new Middle(score, context);
        } else if (score >= 80) {
            new High(score, context);
        } else {
            new Low(score, context);
        }
    }
}
