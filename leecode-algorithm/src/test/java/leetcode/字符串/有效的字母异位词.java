package leetcode.字符串;

/**
 * @author albertliu
 * @className 有效的字母异位词
 * @description
 * 题目描述：
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = “anagram”, t = “nagaram”
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: s = “rat”, t = “car”
 * 输出: false
 *
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * @date 2020/12/29 19:16
 */
public class 有效的字母异位词 {

    public static void main(String[] args) {
        String s = "rat";
        String t = "car";

        System.out.println(yiwei(s,t));
    }

    private static boolean yiwei(String s , String t){
        if(s.length()!=t.length())return false;
        int [] result =  new int[26];
        for(int i = 0; i < s.length();i++){
            result[s.charAt(i)-'a']++;
            result[t.charAt(i)-'a']--;
        }

        for (int i : result) {
            if(i!=0)return false;
        }
        return true;
    }

}
