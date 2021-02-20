package 设计模式.行为行.策略模式.demo1;
/**
 * @author albertliu
 * @className MyCompartor
 * @description TODO
 * @date 2020/10/10 11:32
 */
@FunctionalInterface
public interface MyCompartor {
    int comare(Object o1, Object o2);
}
