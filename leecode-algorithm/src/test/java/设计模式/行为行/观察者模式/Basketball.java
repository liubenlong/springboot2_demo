package 设计模式.行为行.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albertliu
 * @className Basketball
 * @description TODO
 * @date 2020/10/14 14:58
 */
public class Basketball implements Observable{

    private  static List<Observer> observers = new ArrayList<>();

    /**
     * 进了几个球
     */
    private int count;

    public void setCount(int count) {
        this.count = count;
        notifyMsg();
    }

    public int getCount() {
        return count;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyMsg() {
        observers.forEach(observer -> observer.doAction(count));
    }
}
