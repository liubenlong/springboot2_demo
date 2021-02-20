package 设计模式.行为行.策略模式.demo1;
/**
 * @author albertliu
 * @className MyCompartorImpl
 * @description TODO
 * @date 2020/10/10 11:54
 */
public class MyCompartorImpl implements MyCompartor{
    @Override
    public int comare(Object o1, Object o2) {
        if (!(o1 instanceof Cat) || !(o2 instanceof Cat)) {
            throw new IllegalArgumentException("");
        }
        Cat cat1 = (Cat) o1;
        Cat cat2 = (Cat) o2;

        if (cat1.getHeight() > cat2.getHeight()) {
            return -1;
        } else {
            return 1;
        }
    }
}
