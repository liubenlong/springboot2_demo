package 设计模式.行为行.访问者模式;

import java.util.Comparator;
import java.util.List;

/**
 * @author albertliu
 * @className VisitM
 * @description TODO
 * @date 2020/10/14 17:20
 */
public class VisitM implements IVisit {
    @Override
    public List<String> visit(ElementA element) {
        element.getList().sort(Comparator.reverseOrder());
        return element.getList();
    }

    @Override
    public int visit(ElementB element) {
        return element.getA() + 20;
    }
}
