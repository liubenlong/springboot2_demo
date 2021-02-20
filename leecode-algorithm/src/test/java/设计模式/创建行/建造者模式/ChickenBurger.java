package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className VegBurger
 * @description 鸡肉汉堡
 * @date 2020/10/20 16:24
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public int price() {
        return 25;
    }
}
