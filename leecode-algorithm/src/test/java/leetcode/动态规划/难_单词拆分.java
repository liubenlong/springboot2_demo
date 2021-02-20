package leetcode.动态规划;

import java.util.Arrays;
import java.util.List;

/**
 * @author albertliu
 * @className 单词拆分
 * @description
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * https://www.cnblogs.com/OoycyoO/p/11730181.html
 *
 * @date 2020/12/21 16:07
 */
public class 难_单词拆分 {

    public static void main(String[] args) {
        System.out.println(wordBreak("applepenapple", Arrays.asList("apple", "app", "lepena", "pple")));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];   //dp[i]表示子串s.substring(0, i)已在字典中找到匹配
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                //如果dp[j]==false,说明s.substring(0,j)在字典中没有匹配的字符串，则就没有必要测试后续子串s.substring(j, i)
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    //因为必须前面的匹配好了，才有进行后续匹配的必要
                    dp[i] = true;
                    print(dp);
                    break;
                }
            }
        }
        return dp[s.length()];  //如果dp[s.length()]==true，表明sub.substring(0, s.length())已经全部在字典中找到匹配
    }

    private static void print(boolean[] dp) {
        for (boolean b : dp) {
            System.out.print(b + "  ");
        }
        System.out.println();
    }
}
