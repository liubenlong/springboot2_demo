package 有限状态机;

import org.junit.Test;

/**
 * 参考资料：https://blog.csdn.net/yqj2065/article/details/39371487
 */
public class Aircon1 {
    State1 state = State1.OFF;//private改默认，删除getState()。

    //两个Action
    public void power() {//按power键
        state.power(this);
    }

    public void cool() {//按制冷键
        state.cool(this);
    }

    /**
     * ACCtrl的代码。
     */
    @Test
    public void test() {
        Aircon1 ac = new Aircon1();
        System.out.println("Current State:" + ac.state.name());
        ac.cool();
        ac.power();
        ac.cool();
        ac.cool();
        ac.power();
        ac.power();
        ac.power();

    }
}