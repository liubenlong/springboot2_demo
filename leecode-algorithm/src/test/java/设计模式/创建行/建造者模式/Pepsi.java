package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className Coke
 * @description 百事可乐
 * @date 2020/10/20 16:23
 */
public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "百事可乐";
    }

    @Override
    public int price() {
        return 20;
    }
}
