package 设计模式.行为行.模板模式;

/**
 * @author albertliu
 * @className AbstractGame
 * @description TODO
 * @date 2020/10/20 15:08
 */
public abstract class AbstractGame implements IGame{

    @Override
    public final void play() {
        playBasketball();
        playFootball();
    }

}
