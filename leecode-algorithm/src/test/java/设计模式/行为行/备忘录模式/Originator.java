package 设计模式.行为行.备忘录模式;

/**
 * @author albertliu
 * @className Originator
 * @description TODO
 * @date 2020/10/14 14:14
 */
public class Originator {

    public Memento createMemento(int state) {
        return new Memento(state);
    }

    public int getMementoState(Memento memento) {
        return memento == null ? 0 : memento.getState();
    }

}
