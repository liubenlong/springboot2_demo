package leetcode.动态规划.背包;

import java.util.Arrays;

/**
 * 背包问题--分隔等和子集
 * <p>
 * Q：给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * <p>
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 * <p>
 * https://b23.tv/Al7MbI
 * https://www.cnblogs.com/xym4869/p/13023993.html
 */
public class 分隔等和子集 {

    public static void main(String[] args) {
        System.out.println(fenge(new int[]{1, 5, 11, 5}));
        System.out.println(fenge(new int[]{1, 2, 3, 5}));


        System.out.println(fenge2(new int[]{1, 5, 11, 5}));
    }

    public static boolean fenge(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;

        int target = sum / 2;
        /**
         * dp[i][j] = x表示，对于前i个物品，当前背包的容量为j时，
         * 若x为true， 则说明可以恰好将背包装满，
         * 若x为false，则说明不能恰好将背包装满。
         */
        boolean[][] dp = new boolean[nums.length + 1][target + 1];

        for (int i = 1; i <= nums.length; i++) {
            for (int j = i; j <= target; j++) {
                if (nums[i - 1] == j) {
                    for (int n = i; n <= nums.length; n++) {
                        dp[n][j] = true;
                    }
                } else if (nums[i - 1] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        print(dp);
        return dp[nums.length][target];
    }


    /**
     * 优化空间复杂度
     *
     * @param nums
     * @return
     */
    public static boolean fenge2(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;

        int target = sum / 2;
        /**
         * dp[i][j] = x表示，对于前i个物品，当前背包的容量为j时，
         * 若x为true， 则说明可以恰好将背包装满，
         * 若x为false，则说明不能恰好将背包装满。
         */
        boolean[] dp = new boolean[target + 1];

        for (int i = 1; i <= nums.length; i++) {
            for (int j = target; j >= i; j--) {
                if (nums[i - 1] == j) {
                    for (int n = i; n <= nums.length; n++) {
                        dp[j] = true;
                    }
                } else if (nums[i - 1] < j) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }

        print(dp);
        return dp[target];
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

    public static void print(boolean[] dp) {
        for (boolean anInt : dp) {
            System.out.print(anInt + " ");
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
