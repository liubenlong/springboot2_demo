package 设计模式.行为行.状态模式;

/**
 * @author albertliu
 * @className Context
 * @description TODO
 * @date 2020/10/14 15:45
 */
public class ScoreContext {

    private State state;

    public ScoreContext() {
        new Low(0, this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setScore(int score) {
        state.setScore(score);
    }

}
