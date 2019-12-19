import org.junit.Test;

import java.util.*;

public class 字符串相关算法 {


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

    /**
     * 最小窗口子串
     * <p>
     * 示例：
     * <p>
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     * <p>
     * 说明：
     * <p>
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     */
    @Test
    public void test1() {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }


    /**
     * 重复的DNA序列
     */
    @Test
    public void test2() {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    /**
     * 哈希表 解决字符串重复字符的问题
     *
     * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
     */
    @Test
    public void test3() {
        System.out.println(firstNotRepeatingChar("bdaabcc"));
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

    /**
     * 核心思想：
     * 1. 可以使用hashmap，更好的做法是使用  int[]  数组来代替hashmap**
     * 2. end 指针向后遍历，用于扩展区间；end指针向后移动，对应数组中的数字减一
     * 3. begin 指针向后遍历，用于收缩区间；end指针向后移动，对应数组中的数字加一
     * 4. 根据初始化数据（T字符串都初始化为1）以及记录的临时遍历count的值来决定移动begin还是end指针
     * 5. 数组中的值可以减为负值【T字符串的值最大值为1，可以减为负值；其他字符最大值是0】
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (null == s || null == t || s.length() < t.length() || s.length() == 0 || t.length() == 0)
            return "";

        //模拟哈希表，存储目标字符串的各个字符的个数
        int[] map = new int[255];
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }

        //双指针遍历源字符串s
        int begin = 0, end = 0;
        //最小字符串的起点
        int minBegin = 0;
        //最小字符串的长度
        int res = Integer.MAX_VALUE;
        //用来记录匹配到字符的个数，如果count == t.length()意味着找到一个匹配的字串
        int count = 0;

        //遍历
        while (end < s.length()) {
            //这里可理解为缺失字符的个数，==0时则表示 这个字符匹配够了，==1则表示仍需要再匹配一个该字符
            if (map[s.charAt(end)] > 0)
                count++;
            //不需要匹配的字符，其值此时小于0
            map[s.charAt(end)]--;
            //尾指针右移
            end++;
            //匹配到一个子串
            while (count == t.length()) {
                //比较字串长度，更新字串信息
                if (end - begin < res) {
                    res = end - begin;
                    minBegin = begin;
                }
                //如果首指针对应字符是目标字符之一，则跳出循环
                if (map[s.charAt(begin)] == 0)
                    count--;
                //首指针对应字符的哈希值还原
                map[s.charAt(begin)]++;
                //首指针右移
                begin++;
            }
        }

        return res == Integer.MAX_VALUE ? "" : s.substring(minBegin, minBegin + res);
    }


    /**
     * 思路一(简单map记录)：滑动窗口大小10；依次向后滑动，map中记录出现的次数。最后遍历map输出value=1的值
     *
     * 思路二（使用hash表代替map记录）：https://www.jianshu.com/p/02d5f7d33b60
     *
     *
     * 1.设置全局整数哈希int g_hash_map[1048576]; 1048576 = 2^20，表示所有的长度为10的 DNA序列。
     * 2.将DNA字符串的前10个字符使用左移位运算转换为整数key，g_hash_map[key]++。
     * 3.从DNA的第11个字符开始，按顺序遍历各个字符，遇到1个字符即将key右移2位 (去掉最低位)，并且将新的DNA字符s[i]转换为整数后，或运算最高位(第19 、20位)，g_hash_map[key]++。
     * 4.遍历哈希表g_hash_map，若g_hash_map[i] > 1，将i从低到高位转换为10个字符的DNA 序列，push至结果数组。
     *
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len <= 10) return res;

        Map<String, Integer> all = new HashMap<>();

        for (int i = 0; i <= len - 10; i++) {
            String str = s.substring(i, i + 10);
            if (all.containsKey(str)) {
                all.put(str, all.get(str) + 1);
            } else {
                all.put(str, 1);
            }
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : all.entrySet()) {
            if (stringIntegerEntry.getValue() > 1) {
                res.add(stringIntegerEntry.getKey());
            }
        }
        return res;
    }

    public int firstNotRepeatingChar(String str) {
        if ("".equals(str) || null == str)
            return -1;

        int[] hashTable = new int[256];
        for (int i = 0; i < str.length(); i++) {
            hashTable[str.charAt(i)]++;
        }

        //注意：hashTable存储的是每个字符出现的次数。保证第一个不重复次数是因为我们这里根据str来循环的。
        for (int i = 0; i < str.length(); i++) {
            if (1 == hashTable[str.charAt(i)])
                return i;
        }
        return 0;
    }

}