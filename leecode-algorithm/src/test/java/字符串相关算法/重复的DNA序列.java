package 字符串相关算法;

import org.junit.Test;

import java.util.*;

public class 重复的DNA序列 {


    /**
     * 重复的DNA序列
     */
    @Test
    public void test2() {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
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


}