/**
 * 动态规划
 */
public class DP {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) dp[i] = 0x7fff_fffe;
        for (int coin : coins)
            for (int i = coin; i <= amount; i++)
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        return dp[amount] == 0x7fff_fffe ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int i = coinChange(new int[]{1, 2, 5, 7, 10}, 14);
        System.out.print(i);
    }
}
