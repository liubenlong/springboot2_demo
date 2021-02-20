package leetcode.动态规划.背包;

import java.util.Arrays;

/**
 * 背包问题--完全背包（每个物品使用次数是无限制的）
 * <p>
 * //零钱兑换: 最少使用多少枚硬币（可重复），其和为 amount。
 * //凑成金额的最少硬币个数；无法构成则返回-1
 * //如：[1,2,5]  amount=11. 结果=3. 解释：5+5+1
 */
public class 零钱兑换 {

    public static void main(String[] args) {
        System.out.println(lingqian1(new int[]{2, 1, 5}, 11));
        System.out.println(lingqian2(new int[]{2, 1, 5}, 11));
    }

    public static int lingqian1(int[] nums, int target) {
        int[] dp = new int[target + 1];
        for (int num : nums) {//初始化
            dp[num] = 1;
        }
        for (int i = 1; i <= target; i++) {
            if (dp[i] != 1) {
                // min(dp[target-1], dp[target-2], dp[target-5]) + 1
                dp[i] = Math.min(
                        Math.min(i - 1 <= 0 ? Integer.MAX_VALUE : dp[i - 1], i - 2 <= 0 ? Integer.MAX_VALUE : dp[i - 2]),
                        i - 5 <= 0 ? Integer.MAX_VALUE : dp[i - 5]) + 1;
            }
        }

        print(dp);
        return dp[target];
    }

    public static int lingqian2(int[] nums, int target) {
        ///dp[i]: 目标值是i时，需要的最小硬币数量
        int[] dp = new int[target + 1];
        //填充target+1, 因为是取得最小数量，假设都为1的情况，最大为target个硬币，所以这里取target+1，为了下面计算最小值
        Arrays.fill(dp, target + 1);
        dp[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j <= target; j++) {
                dp[j] = Math.min(dp[j], dp[j - nums[i]] + 1);
            }
            print(dp);
        }
        return dp[target];
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
