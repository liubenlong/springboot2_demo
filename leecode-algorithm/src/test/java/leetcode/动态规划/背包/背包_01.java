package leetcode.动态规划.背包;


/**
 * 01背包是指背包的物品个数都为1，也就是只能使用一次。
 * 这类背包问题我们首先要建立一个二维数组dp，其中dp[i][j]表示 前i件物品在体积不超过j的前提下的最大价值。
 * 其中对于每一件物品的体积我们用一维数组w来存储，对于每个物品的价值用一维数组v来存储。
 * 对于一个物品可以有两个状态，一个是添加到背包，二是没有添加到背包，两种情况需要满足以下条件：
 * <p>
 * 第 i 件物品没添加到背包，总体积不超过 j 的前 i 件物品的最大价值就是总体积不超过 j 的前 i-1 件物品的最大价
 * 值，即dp[i][j] = dp[i-1][j]。
 * 第 i 件物品添加到背包中，直接在前i-1件物品在空间不超过j-w[i]最大价值的基础上加上v[i]，即dp[i][j] = dp[i-1][j-w] + v。
 * 以上两种情况的选择取决于谁的价值更大，用公式表示就是：
 * dp[i][j]= max(dp[i-1][j],dp[[i-1][j-w]+v)
 * <p>
 * 核心点在于是否选择使用当前物品
 * <p>
 * 有N=3个物品和重量W=4的背包，第i个物品的重量是wt[i] ,价值是val[i]
 * <p>
 * N = 3, W = 4
 * wt  = [2,1,3]
 * val = [4,2,3]
 * <p>
 * 求最多可以装价值多少的物品？
 */
public class 背包_01 {

    public static void main(String[] args) {
        int n = 3, w = 4;
        int[] wt = new int[]{2, 1, 3};
        int[] val = new int[]{4, 2, 3};

        System.out.println(beibao01(n, w, wt, val));
        System.out.println(beibao02(n, w, wt, val));
    }

    public static int beibao01(int n, int w, int[] wt, int[] val) {
        int[][] dp = new int[n + 1][w + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (wt[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],/*不取当前物品时*/
                            dp[i - 1][j - wt[i - 1]] + val[i - 1]/*取当前物品时. 核心是 j - wt[i - 1] */);
                }
            }
            print(dp);
        }

        return dp[n][w];
    }


    /**
     * 优化空间复杂度，使用一维数组
     * @param n
     * @param w
     * @param wt
     * @param val
     * @return
     */
    public static int beibao02(int n, int w, int[] wt, int[] val) {
        int[] dp = new int[w + 1];

        for (int i = 1; i <= n; i++) {
            //注意这里循环，是从后往前循环的
            for (int j = w; j >= 1; j--) {
                if (wt[i - 1] > j) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = Math.max(
                            dp[j],/*不取当前物品时*/
                            dp[j - wt[i - 1]] + val[i - 1]/*取当前物品时. 核心是 j - wt[i - 1] */);
                }
            }
            print(dp);
        }

        return dp[w];
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
