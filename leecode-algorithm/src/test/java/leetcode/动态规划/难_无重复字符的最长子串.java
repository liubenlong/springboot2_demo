package leetcode.动态规划;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author albertliu
 * @className 无重复字符的最长子串
 * @description TODO
 * @date 2020/12/8 19:31
 */
public class 难_无重复字符的最长子串 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(""));

    }

    //双指针、滑动窗口--set
    //这里使用set。因为滑动窗口内肯定不会有2个相同的字符，因为前面移动过程中早就移动了指针了
    public static int lengthOfLongestSubstringSet(String s) {
        if (null == s || "".equals(s)) return 0;
        if (s.length() == 1) return 1;

        int i = 0, max = 0;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        for (int j = 1; j < s.length(); j++) {
            if (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i));
                i++;
            } else {
                set.add(s.charAt(j));
                max = max > set.size() ? max : set.size();
            }
        }
        return max;

    }

    //双指针、滑动窗口--map
    public static int lengthOfLongestSubstring(String s) {
        int j = 0, maxLength = 0;
        int length = s.length();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.get(c) == null) {
                map.put(c, i);
            } else {
                if (i - j > maxLength) maxLength = i - j;

                j = map.get(c) + 1;
                map.put(c, i);
            }
        }

        return maxLength;
    }


}
