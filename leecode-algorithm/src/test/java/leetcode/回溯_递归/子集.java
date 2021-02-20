package leetcode.回溯_递归;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albertliu
 * @className 子集
 * @description
 * 给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * https://blog.csdn.net/qq_41231926/article/details/82935378
 * @date 2020/12/24 11:15
 */
public class 子集 {

    public static void main(String[] args) {
        int m = 3;
        for(int i = 0 ; i <= m ; i ++){
            ziji(m, i);
        }
    }

    private static void ziji(int m, int k){
        List<ArrayList<Integer>> list = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();

        helper(m, k, list, comb, 1);
        print(list);
    }

    private static void print(List<ArrayList<Integer>> list){
        for (List<Integer> integers : list) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    public static void helper(int n, int k, List<ArrayList<Integer>> combSet, List<Integer> comb, int start) {
        // 一种可能的组合结构
        if (comb.size() == k) {
            combSet.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i <= n; i++) {// 从当前位置开始尝试每个可能的数字
            comb.add(i);
            // after selecting number for current position, process next position
            helper(n, k, combSet, comb, i + 1);
            // clear the current position to try next possible number
            comb.remove(comb.size() - 1);
        }
    }
}
