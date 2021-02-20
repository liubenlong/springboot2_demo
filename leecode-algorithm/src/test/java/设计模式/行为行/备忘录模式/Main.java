package 设计模式.行为行.备忘录模式;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/14 14:20
 */
public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Manager manager = new Manager();

        manager.addMemento(originator.createMemento(1));
        manager.addMemento(originator.createMemento(2));

        System.out.println(originator.getMementoState(manager.getLastedMemento()));

        manager.addMemento(originator.createMemento(3));
        manager.addMemento(originator.createMemento(4));
        System.out.println(originator.getMementoState(manager.getLastedMemento()));

        System.out.println(originator.getMementoState(manager.getMemento(22)));
    }

}
