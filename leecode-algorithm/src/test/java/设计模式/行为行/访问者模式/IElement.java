package 设计模式.行为行.访问者模式;

/**
 * @author albertliu
 * @className IElement
 * @description TODO
 * @date 2020/10/14 17:12
 */
public interface IElement {
    Object accept(IVisit visit);
}
