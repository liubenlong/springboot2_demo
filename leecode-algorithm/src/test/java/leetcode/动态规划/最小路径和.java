package leetcode.动态规划;

/**
 * @author albertliu
 * @className 最小路径和
 * @description
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * @date 2020/12/22 14:42
 */
public class 最小路径和 {
    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        int[][] dp = new int[nums.length][nums[0].length];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = nums[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + nums[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + nums[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + nums[i][j];
                }

                print(dp);
            }
        }

        System.out.println(dp[nums.length - 1][nums[0].length - 1]);
    }

    private static void print(int[][] dp){
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt+"   ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
