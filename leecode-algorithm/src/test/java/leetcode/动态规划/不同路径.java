package leetcode.动态规划;

/**
 * @author albertliu
 * @className 不同路径
 * @description 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 * @date 2020/12/22 14:57
 */
public class 不同路径 {

    public static void main(String[] args) {
        //注意，题目中，n是行
        int m = 3, n = 3;

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

                print(dp);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }

    private static void print(int[][] dp){
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
