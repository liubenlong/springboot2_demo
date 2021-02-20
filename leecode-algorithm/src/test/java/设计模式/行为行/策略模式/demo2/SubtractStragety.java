package 设计模式.行为行.策略模式.demo2;

/**
 * @author albertliu
 * @className AddStragety
 * @description TODO
 * @date 2020/10/20 21:24
 */
public class SubtractStragety implements Strategy {
    @Override
    public int operation(int a, int b) {
        return a-b;
    }
}
