package leetcode.动态规划.背包;

import java.util.Arrays;

/**
 * 背包问题--完全背包（每个物品使用次数是无限制的）
 * <p>
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * <p>
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * <p>
 * 示例 3:
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 * <p>
 * 注意:
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 */
public class 零钱兑换2 {

    public static void main(String[] args) {
        System.out.println(lingqian1(new int[]{1, 2, 5}, 5));
        System.out.println(lingqian1(new int[]{2}, 3));
        System.out.println(lingqian1(new int[]{10}, 10));
    }

    public static int lingqian1(int[] nums, int target) {
        int dp[] = new int[target + 1];
        dp[0] = 1;//注意这里初始化为1，为了方便后面计算

        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j <= target; j++) {
                //典型背包问题：选和不选的当前硬币两种情况
                dp[j] = dp[j] + dp[j - nums[i]];
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
