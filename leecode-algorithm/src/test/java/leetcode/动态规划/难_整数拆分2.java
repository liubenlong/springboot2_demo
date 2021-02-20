package leetcode.动态规划;

/**
 * 整数拆分
 * 给定一个正数，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化，返回你可以获得的最大乘积
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * <p>
 * 说明：你可以假设n不小于2且不大于58.
 *
 *
 * 思路：利用动态规划的思路。用一个数组dp[n + 1]保存每个数字拆分后乘积最大的值。
 * 然后 dp[i] 应该等于每一次试探的最大值。意思就是：每一个数字都应该为dp[i - j] * j 或者 j * (i - j)中较大的那个。
 * 因为得走很多趟，因此还要记录每一趟得最大值，因此代码中用了两个max函数。
 *
 * https://blog.csdn.net/cheng_my/article/details/103119401
 */
public class 难_整数拆分2 {

    public static void main(String[] args) {
        System.out.println(integerBreak(2));
        System.out.println(integerBreak(10));
    }


    public static int integerBreak(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            //我们把i想成一条线，下面for循环，相当于逐个切割。
            //如，i=5，则需要切4刀当切第一刀时，还剩余4，就取 max(1*dp(4), 1*4);
            //当切第二刀时，则取 max(2*dp(3), 2*3)
            //上面2*3，需要乘以3的原因是，dp(3)不包含3，所以要在乘一下，相当于后面的3不在切分。
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(Math.max(j * dp[i - j], j * (i - j)), dp[i]);
            }
        }

        print(dp);
        return dp[n];
    }


    public static void print(int[][] dp) {
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void print(int[] dp) {
        for (int anInt : dp) {
            System.out.print(anInt + " ");
        }
        System.out.println();
    }

}
