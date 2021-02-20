package 设计模式.行为行.责任链模式;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/21 10:11
 */
public class Main {
    public static void main(String[] args) {
        Chain chain = new Chain();
        chain.next(new ConsoleLog())
                .next(new InfoLog())
                .next(new WarnLog())
                .next(new ErrorLog());
        chain.doOperation("hello");


    }
}
