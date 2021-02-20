package leetcode.字符串;

import org.junit.Test;

public class 最长回文子串 {

    /**
     * 参考资料
     *  https://blog.csdn.net/daidaineteasy/article/details/86238047
     *  https://www.toutiao.com/a6607313110350955015/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1546932248&app=news_article&utm_source=weixin&iid=56668232535&utm_medium=toutiao_android&group_id=6607313110350955015
     *
     */
    @Test
    public void test3() {
        System.out.println(longestPalindrome("bbaabcc"));
        System.out.println(longestPalindrome2("bbaabcc"));
        System.out.println(longestPalindrome3("bbaabcc"));
    }

    /**
     * 中心扩展算法
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        if (null == s || s.length() <= 1) {
            return s;
        }

        // 记录回文子串的开始位置
        int start = 0;
        // 记录回文子串的结束位置
        int end = 0;

        for (int i = 0; i < s.length(); i++) {

            // 以每个字符为中心去扩展，例如"aba"就是以'b'为中心
            int len1 = expandAroundCenter(s, i, i);
            // 以两字母之间为中心去扩展，例如 "abba" 的中心在两个 'b'之间
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 找到以left和right为中心的最大回文串长度
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }


    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (null == s || s.length() <= 1) {
            return s;
        }

        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int left = 0;
        int right = 0;

        for (int i = len - 1; i >= 0; i--) {
            // 将对角线（即i==j的情况）赋值为true
            dp[i][i] = true;
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if (dp[i][j] && right - left < j - i) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }



    /**
     * 马拉车算法
     *
     */
    public String longestPalindrome3(String s) {
        // 先预处理字符串
        String str = preHandleString(s);
        // 处理后的字串长度
        int len = str.length();
        // 右边界
        int rightSide = 0;
        // 右边界对应的回文串中心
        int rightSideCenter = 0;
        // 保存以每个字符为中心的回文长度一半（向下取整）
        int[] halfLenArr = new int[len];
        // 记录回文中心
        int center = 0;
        // 记录最长回文长度
        int longestHalf = 0;
        for(int i = 0; i < len; i++) {
            // 是否需要中心扩展
            boolean needCalc = true;
            // 如果在右边界的覆盖之内
            if(rightSide > i) {
                // 计算相对rightSideCenter的对称位置
                int leftCenter = 2 * rightSideCenter - i;
                // 根据回文性质得到的结论
                halfLenArr[i] = halfLenArr[leftCenter];
                // 如果超过了右边界，进行调整
                if(i + halfLenArr[i] > rightSide) {
                    halfLenArr[i] = rightSide - i;
                }
                // 如果根据已知条件计算得出的最长回文小于右边界，则不需要扩展了
                if(i + halfLenArr[leftCenter] < rightSide) {
                    // 直接推出结论
                    needCalc = false;
                }
            }
            // 中心扩展
            if(needCalc) {
                while(i - 1 - halfLenArr[i] >= 0 && i + 1 + halfLenArr[i] < len) {
                    if(str.charAt(i + 1 + halfLenArr[i]) == str.charAt(i - 1 - halfLenArr[i])) {
                        halfLenArr[i]++;
                    } else {
                        break;
                    }
                }
                // 更新右边界及中心
                rightSide = i + halfLenArr[i];
                rightSideCenter = i;
                // 记录最长回文串
                if(halfLenArr[i] > longestHalf) {
                    center = i;
                    longestHalf = halfLenArr[i];
                }
            }
        }
        // 去掉之前添加的#
        StringBuffer sb = new StringBuffer();
        for(int i = center - longestHalf + 1; i <= center + longestHalf; i += 2) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    // 预处理字符串，在两个字符之间加上#
    private String preHandleString(String s) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        sb.append('#');
        for(int i = 0; i < len; i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        return sb.toString();
    }

}
