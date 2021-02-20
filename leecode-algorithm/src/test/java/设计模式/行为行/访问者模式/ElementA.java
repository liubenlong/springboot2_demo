package 设计模式.行为行.访问者模式;

import java.util.Arrays;
import java.util.List;

/**
 * @author albertliu
 * @className ElementA
 * @description TODO
 * @date 2020/10/14 17:15
 */
public class ElementA implements IElement {

    private List<String> list = Arrays.asList("a","b","c");


    @Override
    public Object accept(IVisit visit) {
        return visit.visit(this);
    }

    public List<String> getList() {
        return list;
    }
}
