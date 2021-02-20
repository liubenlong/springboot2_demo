package leetcode.动态规划;

/**
 * @author albertliu
 * @className 解码方法
 * @description 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 题目链接 https://leetcode-cn.com/problems/decode-ways/
 * <p>
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 算法分析
 * 设dp[i]表示以第i个字符为结尾时有多少种编码方式，此时i可以单独成码，有dp[i-1]种方法，
 * 也可以和前面一个字符一起编码，有dp[i-2]种方法，因此状态转移方程可以写为：
 * <p>
 * dp[i]+=dp[i-1] ,if(s[i]!=0)
 * dp[i]+=dp[i-2] ,if(s[i-1]!=0 && s[i-1,i]<=26)
 * <p>
 * 如果出现连续的0, 那么就没有办法分割，返回0。
 * @date 2021/1/15 11:19
 */
public class 难_解码方法 {
    public static void main(String[] args) {
//        System.out.println(numDecodings("2"));
//        System.out.println(numDecodings("0"));
        System.out.println(numDecodings("22"));
//        System.out.println(numDecodings("226"));
//        System.out.println(numDecodings("227"));
//        System.out.println(numDecodings("27"));
//        System.out.println(numDecodings("37"));
//        System.out.println(numDecodings("2002"));
//
//        System.out.println("---------------");
//
//        System.out.println(numDecodings2("2"));
//        System.out.println(numDecodings2("0"));
//        System.out.println(numDecodings("22"));
//        System.out.println(numDecodings2("226"));
//        System.out.println(numDecodings2("227"));
//        System.out.println(numDecodings2("27"));
//        System.out.println(numDecodings2("37"));
//        System.out.println(numDecodings2("2002"));

    }

    public static int numDecodings(String s) {
        if (null == s || "".equals(s) || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length() + 1];//长度为n时，的排列组合种类
        dp[0] = 1;
        dp[1] = 1;
        if (s.length() == 1) return dp[1];

        for (int i = 2; i <= s.length(); i++) {
            int num = Integer.valueOf(String.valueOf(s.charAt(i - 1)));//得到当前数；
            int nums2 = Integer.valueOf(String.valueOf(s.charAt(i - 2)));//得到当前数的前一个数

            if (nums2 + num == 0 || (num == 0 && nums2 > 2)) {
                return 0;
            } else if (num == 0 || nums2 == 0) {
                dp[i] = num == 0 ? dp[i - 2] : dp[i - 1];
            } else {
                dp[i] = nums2 * 10 + num > 26 ? dp[i - 1] : dp[i - 2] + dp[i - 1];
            }

        }
        return dp[s.length()];
    }

    /**
     * 这个有问题
     * 227输出不对
     *
     * @param s
     * @return
     */
    public static int numDecodings2(String s) {
        if (null == s || "".equals(s) || s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;

        int n = s.length();
        int[] dp = new int[n];

        dp[0] = 1;
        if (s.charAt(1) != '0') ++dp[1];
        if (Integer.parseInt(s.substring(0, 2)) <= 26) ++dp[1];

        for (int i = 2; i < n; ++i) {
            if (s.charAt(i) != '0') dp[i] += dp[i - 1];
            if (s.charAt(i-1) == '0' && s.charAt(i) == '0') return 0;
            if (s.charAt(i-1) != '0' && Integer.parseInt(s.substring(i-1, 2)) <= 26) dp[i] += dp[i - 2];
        }

        return dp[n - 1];
    }

}