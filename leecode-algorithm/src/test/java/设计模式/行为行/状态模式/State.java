package 设计模式.行为行.状态模式;

/**
 * @author albertliu
 * @className State
 * @description TODO
 * @date 2020/10/14 15:44
 */
public interface State {

    void setScore(int score);

    String getName();

    int getScore();
}
