package 设计模式.行为行.模板模式;

/**
 * @author albertliu
 * @className IGame
 * @description TODO
 * @date 2020/10/20 15:06
 */
public interface IGame {
    /**
     * 玩。
     * 模板方法, 定义算法流程
     */
    void play();

    void playBasketball();

    void playFootball();
}
