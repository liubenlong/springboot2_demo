package 字符串相关算法;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 无重复字符串最长子串长度 {
    /**
     * 无重复字符串最长子串，只输出子串长度
     */
    @Test
    public void test() {
        String str = "abcbbcodebb";
        System.out.println(lengthOfLongestSubstring(str));
        System.out.println(lengthOfLongestSubstring1(str));
        System.out.println(lengthOfLongestSubstring2(str));
        System.out.println(lengthOfLongestSubstring3(str));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int from = 0, to = 1, length = 1, maxLength = 0;
        // to遍历直到字符串末尾
        while (to < s.length()) {
            int site = s.substring(from, to).indexOf(s.charAt(to));//这里也可以使用hashset来空间换时间
            if (site != -1) {
                // to指向的字符已存在
                length = to - from;
                if (length > maxLength) {
                    maxLength = length;
                }
                // 判断是否需要继续遍历
                if (maxLength > s.length() - from + 1) return maxLength;
                from = from + site + 1;
            }
            to++;
        }
        // 处理最后一个子串
        if (to - from > maxLength) {
            maxLength = to - from;
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }



}
