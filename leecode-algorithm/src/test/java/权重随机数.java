import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*** 
 * java 权重随机数生成。
 * 如抽奖，每个奖项获奖概率不同。
 *
 * 假如 A 30%；B 20% ; C 50% 概率中奖
 *
 * 方法一： 30+20+50=100，设计一个100长度的数组，数组内有30个A，20个B，50个C，乱序排放。然后 java.util.random.next(100)，从数组中取值即可。
 *
 * 方法二：将权重数组按照从大到小顺序排列。然后采用 r=Math.random() ,计算r落在哪个位置区间
 *
 *  权重数组排序 [0      50   80  100]
 *               |_______|____|___|
 *  区间概率：   0     0.5   0.8  1
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Node {
    private double score;
    private String name;
}

public class 权重随机数 {

    public static void main(String[] args) {
        Node[] nodes = {Node.builder().name("A").score(40d).build(),
                Node.builder().name("B").score(20d).build(),
                Node.builder().name("C").score(40d).build()};

        int a = 0, b = 0, c = 0;
        for (int i = 0; i < 2000000; i++) {
            String str = getWeightRandom(nodes);
            switch (str) {
                case "A":
                    a++;
                    break;
                case "B":
                    b++;
                    break;
                case "C":
                    c++;
                    break;
                default:
                    System.out.println("error");
                    break;
            }
        }

        System.out.println("执行200W次，结果取到 a 的概率：" + a / 2000000d);//d: double类型数据
        System.out.println("执行200W次，结果取到 b 的概率：" + b / 2000000d);
        System.out.println("执行200W次，结果取到 c 的概率：" + c / 2000000d);
    }

    /***
     * @param nodes 权重数组
     */
    public static String getWeightRandom(Node[] nodes) {
        double weightSum = 0, stepWeightSum = 0;
        for (Node node : nodes) {
            weightSum += node.getScore();
        }

        /*
         将权重数组按照大到小排序，那么落在某个区间的概率就是权重的概率
         [0   20     50      100]
          |____|______|_______|
         */
        double r = Math.random();//结果0-1. 由于这里是0-1，所以权重分值的和加起来要等于100

        for (int i = 0; i < nodes.length; i++) {
            // 计算权重值
            stepWeightSum += nodes[i].getScore();
            // 如果随机数落在了权重区间则返回索引值  
            if (r <= stepWeightSum / weightSum) {
                return nodes[i].getName();
            }
        }
        return null;
    }
}  