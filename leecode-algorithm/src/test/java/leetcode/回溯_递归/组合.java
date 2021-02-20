package leetcode.回溯_递归;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albertliu
 * @className 组合
 * @description 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * https://www.cnblogs.com/ariel-dreamland/p/9154419.html
 * https://blog.csdn.net/u010500263/article/details/18435495
 * @date 2020/12/24 11:15
 */
public class 组合 {

    public static void main(String[] args) {
        List<ArrayList<Integer>> list = combine(5, 3);
        print(list);
    }

    private static void print(List<ArrayList<Integer>> list) {
        for (List<Integer> integers : list) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }


    public static List<ArrayList<Integer>> combine(int n, int k) {
        List<ArrayList<Integer>> combSet = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();

        if (n < k) return combSet;
        helper(n, k, combSet, comb, 1);
        return combSet;
    }

    public static void helper(int n, int k, List<ArrayList<Integer>> combSet, List<Integer> comb, int start) {
        // 一种可能的组合结构
        if (comb.size() == k) {
            combSet.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i <= n; i++) {// 从当前位置开始尝试每个可能的数字
            comb.add(i);
            // 上面往comb里加了一个数，接下来递归，从剩余的数中再添加一个。
            helper(n, k, combSet, comb, i + 1);
            //递归结束后，要将当前数移除后，在进行下一个循环
            comb.remove(comb.size() - 1);
        }
    }


}
