package 设计模式.行为行.策略模式.demo2;

/**
 * @author albertliu
 * @className Calculate
 * @description TODO
 * @date 2020/10/20 21:25
 */
public class Calculate {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int operation(int a, int b) {
        return this.strategy.operation(a, b);
    }

}
