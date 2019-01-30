package 观察者模式;

/***
 * 被观察者接口
 * 声明了添加、删除、通知观察者方法
 *
 */
public interface Observerable {

    /**
     * 注册观察者
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 删除观察者
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * 通知
     */
    void notifyObserver();
    
}