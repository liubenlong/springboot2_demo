package 观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 */
public class WechatServer implements Observerable {

    /**
     * 观察者集合
     */
    private List<Observer> observers = new ArrayList<>();

    /**
     * 消息
     */
    private String msg;


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (!observers.isEmpty()) {
            observers.remove(o);
        }
    }

    /**
     * 接收消息以后，遍历所有的observers进行通知
     */
    @Override
    public void notifyObserver() {
        observers.forEach(observer -> observer.update(msg));
    }

    public void setInfomation(String s) {
        this.msg = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
