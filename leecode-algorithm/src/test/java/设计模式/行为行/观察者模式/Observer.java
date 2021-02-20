package 设计模式.行为行.观察者模式;

/**
 * @author albertliu
 * @className Observer
 * @description 观察者接口
 * @date 2020/10/14 14:35
 */
public interface Observer {
    /**
     * 观察到被观察者有变动时处理
     */
    void doAction(int count);
}
