package DP;

import org.junit.Test;

public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) dp[i] = Integer.MAX_VALUE;

        for (int coin : coins)
            for (int i = coin; i <= amount; i++)
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * coinChange
     * <p>
     * https://blog.csdn.net/lym940928/article/details/90111598
     * https://v.youku.com/v_show/id_XMzg0MDcyMDI0MA==.html?
     */
    @Test
    public void test1() {
        int i = coinChange(new int[]{1, 2, 5, 7, 10}, 14);
        System.out.print(i);
    }
}
