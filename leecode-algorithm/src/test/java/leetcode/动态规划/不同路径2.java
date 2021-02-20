package leetcode.动态规划;

/**
 * @author albertliu
 * @className 不同路径
 * @description
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * @date 2020/12/22 14:57
 */
public class 不同路径2 {

    public static void main(String[] args) {
        //注意，题目中，n是行
        int m = 3, n = 3;
        int[][] zhangai = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        int[][] dp = new int[3][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (zhangai[i - 1][j] == 1) {
                    dp[i][j] = dp[i][j - 1];
                } else if (zhangai[i][j - 1] == 1) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
                print(dp);
            }
        }

        System.out.println();

        System.out.println(dp[m - 1][n - 1]);
    }

    private static void print(int[][] dp) {
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
