package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className Wrapper
 * @description 纸盒
 * @date 2020/10/20 16:20
 */
public class Wrapper implements Packing{
    @Override
    public String pack() {
        return "Wrapper";
    }
}
