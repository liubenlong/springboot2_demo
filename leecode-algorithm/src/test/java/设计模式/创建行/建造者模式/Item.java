package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className Item
 * @description 实物组成条目
 * @date 2020/10/20 15:56
 */
public interface Item {
    String name();
    int price();
    Packing packing();
}
