package leetcode.动态规划;

/**
 * @author albertliu
 * @className 最大子序列和
 * @description 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 输入:      [-2,1,-3,4,-1,2,1,-5,4],
 * 当前最大值：[-2,1,-2,4,3, 5,6,1, 5],
 * 历史最大值：[-2,1,1, 4,4, 5,6,6, 6],
 *
 * https://www.cnblogs.com/RioTian/p/13154733.html
 *
 * @date 2020/12/21 10:38
 */
public class 最大子序列和 {
    public static void main(String[] args) {
        System.out.println(xulie(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }


    public static int xulie(int[] nums) {
        int maxSum = nums[0];//历史最大序列和
        int currentMaxSum = nums[0];//当前最大值
        for (int i = 1; i < nums.length; i++) {
            currentMaxSum = Math.max(nums[i] + currentMaxSum, nums[i]);
            maxSum = Math.max(maxSum, currentMaxSum);
        }
        return maxSum;
    }
}
