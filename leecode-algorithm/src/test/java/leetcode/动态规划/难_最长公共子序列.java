package leetcode.动态规划;

/**
 * @author albertliu
 * @className 两个字符串的最长公共子序列
 * @description 动态规划 https://blog.csdn.net/weixin_40673608/article/details/84262695
 * @date 2020/12/16 14:15
 */
public class 难_最长公共子序列 {
    public static void main(String[] args) {
        String a = "bdca", b = "abcb";
        int[][] dp = new int[b.length()][a.length()];

        for (int i = 0; i < b.length(); i++) {
            for (int j = 0; j < a.length(); j++) {
                if (i == 0 || j == 0) {
                    if (a.charAt(j) == b.charAt(i)) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (a.charAt(j) == b.charAt(i)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
        }
        System.out.println("max = " + dp[b.length() - 1][a.length() - 1]);
    }

}
