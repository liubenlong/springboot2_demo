package 设计模式.行为行.策略模式.demo1;
import lombok.Data;

/**
 * @author albertliu
 * @className Cat
 * @description TODO
 * @date 2020/10/10 11:31
 */
@Data
public class Cat implements MyComparable {
    private int age;
    private int height;
    private int weight;

    @Override
    public int compareTo(Object o, MyCompartor myCompartor) {
        return myCompartor.comare(this, o);
    }
}
