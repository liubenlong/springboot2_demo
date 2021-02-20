package leetcode.动态规划.背包;

import java.util.Arrays;

/**
 * 问题三：1和 0（LeetCode 474）
 * 题目描述：
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * 注意:
 * 给定 0 和 1 的数量都不会超过 100。
 * 给定字符串数组的长度不会超过 600。
 * 示例 1:
 * 输入: Array = {“10”, “0001”, “111001”, “1”, “0”}, m = 5, n = 3
 * 输出: 4
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 “10”,“0001”,“1”,“0” 。
 * 示例 2:
 * 输入: Array = {“10”, “0”, “1”}, m = 1, n = 1
 * 输出: 2
 * 解释: 你可以拼出 “10”，但之后就没有剩余数字了。更好的选择是拼出 “0” 和 “1” 。
 * <p>
 * 解题思路
 * 二维dp，多加一层循环就可以
 * <p>
 * https://b23.tv/LubAr9
 */
public class 难_DP_1和0 {

    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0" }, 5, 3));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0)
            return 0;
        //dp: 剩余空间为m,n的时候，最多可以装多少个物品
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < strs.length; i++) {
            //对每个物品的0和1的个数进行统计
            int ones = 0, zeros = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0')
                    zeros++;
                if (strs[i].charAt(j) == '1')
                    ones++;
            }
            //
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeros][k - ones] + 1);
                }
            }

            print(dp);
        }
        return dp[m][n];
    }

    public static void print(boolean[][] dp) {
        for (boolean[] ints : dp) {
            for (boolean anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
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
