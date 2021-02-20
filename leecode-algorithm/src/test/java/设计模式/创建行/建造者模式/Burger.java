package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className Burger
 * @description 汉堡
 * @date 2020/10/20 16:00
 */
public abstract class Burger implements  Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public String toString() {
        return this.packing()+" " + this.name() + " " + this.price();
    }
}
