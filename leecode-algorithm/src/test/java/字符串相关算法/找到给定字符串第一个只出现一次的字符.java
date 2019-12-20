package 字符串相关算法;

import org.junit.Test;

public class 找到给定字符串第一个只出现一次的字符 {

    /**
     * 哈希表 解决字符串重复字符的问题
     *
     * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
     */
    @Test
    public void test3() {
        System.out.println(firstNotRepeatingChar("bdaabcc"));
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
