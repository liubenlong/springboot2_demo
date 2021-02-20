package 设计模式.结构行.适配器模式.对象适配器;

/**
 * @author albertliu
 * @className ACAdapter
 * @description TODO
 * @date 2020/10/20 21:02
 */
public interface ACAdapter {
    boolean support(AC ac);
    int adapter(AC ac);
}
