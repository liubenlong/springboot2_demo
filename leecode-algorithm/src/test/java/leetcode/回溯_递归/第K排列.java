package leetcode.回溯_递归;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albertliu
 * @className tst
 * @description https://blog.csdn.net/weixin_40673608/article/details/88247639
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 * @date 2020/12/24 9:58
 */
public class 第K排列 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<int[]> list = new ArrayList<>();
        pailie(nums, 0, nums.length - 1, 5, list);
        print(list.get(list.size() - 1));
    }

    private static void pailie(int[] nums, int begin, int end, int target, List<int[]> result) {
        if (begin == end) {
            int[] temp = new int[nums.length];
            System.arraycopy(nums, 0, temp, 0, nums.length);
            result.add(temp);
            if (result.size() >= target) return;
        }

        for (int i = begin; i <= end; i++) {
            swap(nums, i, begin);
            pailie(nums, begin + 1, end, target, result);
            if (result.size() >= target) return;
            swap(nums, i, begin);
        }
    }


    private static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num);
        }
        System.out.println();
    }

    private static void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }
}
