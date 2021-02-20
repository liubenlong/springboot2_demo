package 设计模式.行为行.访问者模式;

import java.util.List;

/**
 * @author albertliu
 * @className IVisit
 * @description TODO
 * @date 2020/10/14 17:12
 */
public interface IVisit {
    List<String> visit(ElementA element);
    int visit(ElementB element);
}
