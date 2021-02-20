package leetcode.字符串;

import java.util.HashSet;
import java.util.Set;

/**
 * @author albertliu
 * @className 重复的DNA序列
 * @description 所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。
 * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * <p>
 * 编写一个函数来查找 DNA 分子中所有出现超多一次的10个字母长的序列（子串）。
 * <p>
 * 示例:
 * 输入: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出: ["AAAAACCCCC", "CCCCCAAAAA"]
 * @date 2020/12/29 17:24
 */
public class 重复的DNA序列 {

    public static void main(String[] args) {

        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        int n = 10;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < s.length() - n - 1; i++) {
            String str = s.substring(i, i + 10);
            boolean b = set.add(str);
            if (!b) {
                System.out.println(str);
            }
        }
    }


}
