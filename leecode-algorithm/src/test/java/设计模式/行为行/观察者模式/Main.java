package 设计模式.行为行.观察者模式;

/**
 * @author albertliu
 * @className Main
 * @description 观察者模式
 *
 * @date 2020/10/14 11:37
 */
public class Main {
    public static void main(String[] args){
        Basketball basketball = new Basketball();

        basketball.addObserver(new User());
        basketball.addObserver(new User1());

        basketball.setCount(1);
        basketball.setCount(10);
    }
}
