package 设计模式.行为行.备忘录模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albertliu
 * @className Manager
 * @description TODO
 * @date 2020/10/14 14:11
 */
public class Manager {
    private static List<Memento> list = new ArrayList<>();

    public void addMemento(Memento memento) {
        list.add(0, memento);
    }

    public Memento getLastedMemento() {
        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * 获取倒数第几个状态
     * @param i
     * @return
     */
    public Memento getMemento(int i) {
        return list.size() < i ? null : list.get(i-1);
    }
}
