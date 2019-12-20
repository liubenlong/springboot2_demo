package DP;

import org.junit.Test;

public class 爬楼梯 {

    @Test
    public void test() {
        for (int i = 1; i < 10; i++) {
            System.out.println("dp[" + i + "]=" + climbStairs(i));
        }
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 2];//0-n 多一位是为了后面for循环越界问题
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
