package 设计模式.行为行.观察者模式;

/**
 * @author albertliu
 * @className User
 * @description 观察者
 * @date 2020/10/14 14:38
 */
public class User implements Observer {

    @Override
    public void doAction(int count) {
        System.out.println("进了" + count + "个球，我要去喝一杯");
    }

}
