package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className Coke
 * @description 可口可乐
 * @date 2020/10/20 16:23
 */
public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "可口可乐";
    }

    @Override
    public int price() {
        return 10;
    }
}
