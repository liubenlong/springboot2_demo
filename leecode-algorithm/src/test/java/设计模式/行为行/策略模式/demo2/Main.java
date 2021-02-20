package 设计模式.行为行.策略模式.demo2;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/20 21:26
 */
public class Main {
    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        calculate.setStrategy(new AddStragety());
        System.out.println(calculate.operation(2,3));

        calculate.setStrategy(new MultiplyStragety());
        System.out.println(calculate.operation(2,3));

        calculate.setStrategy(new SubtractStragety());
        System.out.println(calculate.operation(2,3));
    }
}
