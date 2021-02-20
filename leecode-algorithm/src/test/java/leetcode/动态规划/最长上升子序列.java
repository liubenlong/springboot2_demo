package leetcode.动态规划;

/**
 * @author albertliu
 * @className 最长上升子序列
 * @description 最长上升子序列不是最大上升子序列，思想一样但结果可能不一样
 * <p>
 * 比如：序列(100, 1, 2, 3)的最大上升子序列和为100，而最长上升子序列为(1, 2, 3)。
 * <p>
 * 现在我们要解决的问题就是在给定的数组中找出最长上升子序列：先给一个栗子
 * <p>
 * 给定的数组 a[7] = {1,6,4,2,3,9,8}
 * <p>
 * 思想：这里用到的是一个自底向上的寻找最优子结构的的思想。粗俗来说：如果你想要得到七个数里面的最长子序列，
 * 你可以先找前6个数里面的最长子序列，同理，你又必须得找前5个数里面的最长子序列，直到子序列为1
 * <p>
 * 大体的步骤是这样的
 * <p>
 * d[i]:用数组d 来存储前第 i 个数的最长子序列,i 表示的就前几个数
 * <p>
 * 毫无疑问
 * {1}  -------------------------------d[1]=1 : 表示第一个数他的最长子序列是1      {1}
 * {1,6}  --------------------------------d[2]=d[1]+1=2 : 表示前两个数中，最长上升子序列为2   {1,6}
 * {1,6,4}  --------------------------------d[3]=d[1]+1=2 : 因为4 < 6 所以不能用 d[2]+1,但 1<4 所以 是 d[1]+1。 {1,6},{1,4} 等
 * {1,6,4,2} ---------------------------------d[4]=d[1]+1=2 : 4和6 都大于2 所以不能用d[2],d[3]。 {1,6},{1,4} 等
 * {1,6,4,2,3}  ---------------------------------d[5]=d[4]+1=3 : 3 >2 所以 可以d[4]+1=2+1=3  。 {1,2,3}
 * {1,6,4,2,3,9} ----------------------------------d[6]=d[5]+1= 4 : d[5]+1=3+1=4  。 {1,2,3,9}
 * {1,6,4,2,3,9,8} ----------------------------------d[7]=4   ：        {1,2,3,9} 或者{1,2,3,8}
 * @date 2020/12/21 16:34
 */
public class 最长上升子序列 {
    public static void main(String[] args) {
        System.out.println(dp(new int[]{1, 6, 4, 2, 3, 9, 8}));
        System.out.println(dp(new int[]{2, 2, 2}));
        System.out.println(dp(new int[]{2, 3}));
        System.out.println(dp(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(dp(new int[]{0, 1, 0, 3, 2, 3, 0}));
    }


    public static int dp(int[] nums) {
        if (null == nums || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;//充当初始化工作
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }

//        print(dp);
        return result;
    }

    public static void print(int[] dp) {
        for (int anInt : dp) {
            System.out.print(anInt + " ");
        }
        System.out.println();
    }

}
