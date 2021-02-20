package 设计模式.行为行.备忘录模式;

/**
 * @author albertliu
 * @className Memento
 * @description 备忘录
 * @date 2020/10/14 14:11
 */
public class Memento {

    private int state;

    public Memento(int state){
        this.state=state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
