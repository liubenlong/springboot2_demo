package leetcode.动态规划;

/**
 * https://b23.tv/9xoXeV
 */
public class 难_最长回文子串 {

    public static void main(String[] args) {
        System.out.println(huiwen("babad"));
        System.out.println(huiwen("cbbd"));
        System.out.println(huiwen("a"));
        System.out.println(huiwen("ddcdcd"));
    }

    /**
     * 动态规划
     * @param str
     * @return
     */
    private static String huiwen(String str) {
        String result = "";
        int length = str.length();
        int[][] dp = new int[length][length];

        for (int l = 1; l <= length; l++) {//第一层循环，长度。长度从1开始，比较好理解，但要注意下面-1
            for (int i = 0; i + l <= length; i++) {//第二层循环，行
                if (l == 1) {
                    dp[i][i + l - 1] = 1;
                } else if (l == 2 && str.charAt(i) == str.charAt(i + l - 1)) {
                    dp[i][i + l - 1] = 1;
                } else if (str.charAt(i) == str.charAt(i + l - 1) && dp[i + 1][i + l - 1 - 1] == 1) {
                    dp[i][i + l - 1] = 1;
                }
                if (dp[i][i + l - 1] == 1) {
                    result = str.substring(i, i + l - 1 + 1);
                }
            }
//            print(dp);
        }

        return result;
    }

    public static String huiwen1(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = 1;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) ? 1 : 0);
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) ? 1 : 0;
                }
                if (dp[i][j] == 1 && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
            print(dp);
        }
        return ans;
    }


    public static void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}