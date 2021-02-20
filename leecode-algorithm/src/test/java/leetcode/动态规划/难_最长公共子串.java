package leetcode.动态规划;

/**
 * @author albertliu
 * @className 两个字符串的最长公共子串
 * @description 动态规划 https://www.cnblogs.com/fanguangdexiaoyuer/p/11281179.html
 * 有两个字符串（可能包含空格）,请找出其中最长的公共连续子串,输出其长度。(长度在1000以内)
 * 例如：
 * 输入：abcde bcd
 * 输出：3
 * @date 2020/12/16 14:15
 */
public class 难_最长公共子串 {
    public static void main(String[] args) {
        String a = "acbcbcef", b = "abcbced";
        int[][] dp = new int[b.length()][a.length()];
        int max = 0;
        String maxStr = "";

        for (int i = 0; i < b.length(); i++) {
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) == b.charAt(i)) {
                    if (i == 0 || j == 0) dp[i][j] = 1;
                    else dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        maxStr = b.substring(i - max < 0 ? 0 : i - max + 1, i + 1 );
                    }
                }
            }
        }
        System.out.println("max = " + max + ", maxStr=" + maxStr);
    }

}
