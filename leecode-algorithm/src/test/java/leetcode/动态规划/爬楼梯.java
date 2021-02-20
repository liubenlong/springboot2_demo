package leetcode.动态规划;

/**
 * @author albertliu
 * @className 爬楼梯
 * @description TODO
 * @date 2020/12/20 17:05
 */
public class 爬楼梯 {
    public static void main(String[] args) {
        System.out.println(dp(1));
        System.out.println(dp(2));
        System.out.println(dp(3));
        System.out.println(dp(4));
        System.out.println(dp(5));
    }

    public static int dp(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 2;
        else {
            int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n - 1];
        }
    }
}
