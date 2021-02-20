package 设计模式.行为行.观察者模式;

/**
 * @author albertliu
 * @className Observerable
 * @description
 * @date 2020/10/14 14:36
 */
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyMsg();
}
