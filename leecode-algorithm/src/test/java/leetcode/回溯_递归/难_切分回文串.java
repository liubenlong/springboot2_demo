package leetcode.回溯_递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输出所有回文串切割组合。
 * 如  aab ,输出  [a,a,b][aa,b]
 */
public class 难_切分回文串 {
    public static void main(String[] args) {
        //是否是回文串
//        System.out.println(huiwen("a"));
//        System.out.println(huiwen("aa"));
//        System.out.println(huiwen("ab"));
//        System.out.println(huiwen("aba"));
//        System.out.println(huiwen("abc"));
//        System.out.println(huiwen("abba"));

        /*
            回溯算法：回溯算法通常使用递归实现，但是比递归多了【恢复操作】，添加新元素后，在下一次遍历之前，会恢复到刚进入本次遍历的状态
            回溯函数通常有这三个参数：
            1. 上一步回溯做出的选择
            2. 当前选择
            3. 所有可选择的结果集

            通常使用fot循环来实现当前选择的变换与下一步递归的进入
        */
        String str = "aab";
        ArrayList<List<String>> result = new ArrayList<>();
        dfs(str, 0, str.length() - 1, null, result);

        for (List<String> strings : result) {
            for (String s : strings) {
                System.out.print(s + ",");
            }
            System.out.println();
        }
    }

    public static void dfs(String str, int start, int end, List<String> cur, List<List<String>> result) {
        if (start > end) {
            result.add(new ArrayList<>(cur));
        }

        for (int i = start; i < str.length(); i++) {
            String substring = str.substring(start, i + 1);
            if (huiwen(substring)) {
                if (null == cur) cur = new ArrayList<>();
                cur.add(substring);
                dfs(str, i + 1, end, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }


    /**
     * 判断是不是回文串
     *
     * @param str
     * @return
     */
    public static boolean huiwen(String str) {
        if (null == str || "".equals(str)) return false;
        if (str.length() == 1) return true;

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) return false;
        }
        return true;

    }


    public static void print(int[][] dp) {
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
