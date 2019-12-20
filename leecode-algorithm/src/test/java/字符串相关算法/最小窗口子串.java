package 字符串相关算法;

import org.junit.Test;

public class 最小窗口子串 {

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

}
