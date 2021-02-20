package 设计模式.行为行.访问者模式;

/**
 * @author albertliu
 * @className ElementA
 * @description TODO
 * @date 2020/10/14 17:15
 */
public class ElementB implements IElement {

    private int a = 10;


    @Override
    public Object accept(IVisit visit) {
        return visit.visit(this);
    }

    public int getA() {
        return a;
    }
}
