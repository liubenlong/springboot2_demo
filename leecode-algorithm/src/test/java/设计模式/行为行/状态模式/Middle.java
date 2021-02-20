package 设计模式.行为行.状态模式;

/**
 * @author albertliu
 * @className SAtartState
 * @description TODO
 * @date 2020/10/14 15:46
 */
public class Middle implements State {

    private int score;
    private ScoreContext context;

    public Middle(int score, ScoreContext context) {
        this.score=score;
        this.context = context;
        context.setState(this);
    }

    @Override
    public void setScore(int score) {
        StateUtil.modifyState(score, context);
        System.out.println("current score:" + score + " State: " + context.getState().getName());
    }

    @Override
    public String getName() {
        return "Middle";
    }
    @Override
    public int getScore() {
        return score;
    }
}
