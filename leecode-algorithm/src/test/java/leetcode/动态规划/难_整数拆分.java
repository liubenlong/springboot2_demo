package leetcode.动态规划;

/**
 * 整数拆分
 * 将正整数n无序拆分成最大数为m的拆分方案个数，要求所有拆分方案不重复。
 * <p>
 * （1）当n=1，无论m为多少，只有{1}一种划分
 * （2）当m=1，无论n为多少，只有{1,1,1…}一种划分
 * （3）当n<m，f（n，m）=f（n，n）
 * （4）当n=m，如果划分中有n，则只有{n}一种划分；当划分中没有n，则f（n，n）=f（n，n-1）
 * f（n，n）= 1 + f（n，n - 1）
 * （5）[重点：考虑切分时有m的情况和无m的情况]
 * 当n>m，如果划分中有m，则{m，{x1，x2，…} = n - m}，f（n，m）=f（n - m， m）；
 * 当划分中没有m，f（n，m）=f（n，m - 1）
 * 可以简单理解为，拆分为两部分。如n=5,m=3时，拆分结果有3时，先拆出一个完整的3，然后剩余2，取dp[2,2]（表示n=2, m=2）即可。
 * 可以简单理解为不含m时，就是n=5, m=2了
 * <p>
 * 结论：f（n，m） = f（n - m， m）+ f（n，m - 1）
 */
public class 难_整数拆分 {

    public static void main(String[] args) {
        System.out.println(integerhuafen(5, 5));
        System.out.println(chaifen(4,4));
    }

    /*
    推荐该方法：多了一行一列，便于计算与理解
     */
    public static int integerhuafen(int n, int m) {
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 || j == 1) {
                    dp[i][j] = 1;
                } else if (i == j) {
                    dp[i][j] = 1 + dp[i][j - 1];
                } else if (i < j) {
                    dp[i][j] = dp[i][i];
                } else {
                    dp[i][j] = dp[i - j][j] + dp[i][j - 1];
                }
            }
            print(dp);
        }
        return dp[n][m];
    }


    /**
     * 将n拆分为最大值为m的方案的数量，不含重复
     * 
     */
    public static int chaifen(int n, int m) {
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else if (j > i) {
                    dp[i][j] = dp[i][j - 1];
                } else if (i == j) {
                    dp[i][j] = 1 + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - j - 1][j] + dp[i][j - 1];
                }
            }
            print(dp);
        }
        return dp[n - 1][m - 1];
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
