package 设计模式.行为行.访问者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albertliu
 * @className ElementSet
 * @description 元素集合
 * @date 2020/10/21 10:54
 */
public class ElementSet {
    private List<IElement> elements = new ArrayList<>();

    public void addElement(IElement e){
        elements.add(e );
    }


    public void removeElement(IElement e){
        elements.remove(e );
    }

    public List accept(IVisit iVisit){
        List list = new ArrayList();
        for (int i = 0; i < elements.size(); i++) {
            list.add(elements.get(i).accept(iVisit));
        }
        return list;
    }

}
