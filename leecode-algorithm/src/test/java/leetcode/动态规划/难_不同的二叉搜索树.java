package leetcode.动态规划;

/**
 * @author albertliu
 * @className 不同的二叉搜索树
 * @description TODO
 * @date 2021/1/22 11:35
 */
public class 难_不同的二叉搜索树 {

    public static void main(String[] args) {
        //不同的二叉搜索树
        int n = 3;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            //求和
            for (int j = 1; j <= i; j++) {
                //  dp[根节点左边节点数数量] * dp[根节点右边节点数量]
                dp[i] += dp[i - (i - j) - 1] * dp[i - j];
            }
        }
        System.out.println(dp[n]);
    }
}
