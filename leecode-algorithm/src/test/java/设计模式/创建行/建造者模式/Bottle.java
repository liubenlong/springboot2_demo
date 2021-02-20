package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className Bottle
 * @description 瓶子
 * @date 2020/10/20 16:21
 */
public class Bottle implements Packing{
    @Override
    public String pack() {
        return "Bottle";
    }
}
