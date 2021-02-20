package leetcode.动态规划;

/**
 * @author albertliu
 * @className 乘积最大序列
 * @description https://blog.csdn.net/clearLB/article/details/104547200/
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 分析：
 * 本题存在的一个难点就是：一个最大的乘积的连续序列乘以一个负数后，就会变成最小的，
 * 同样，本来是最小的乘积的连续序列乘以一个负数后，有可能会变成最大的，
 * 如果参照最大子序列和一样只设置一个dp数组，dp[i]用来表示包含第i个元素的前i个元素的乘积最大的连续子序列，
 * 那么显然不好写出状态转移方程，具体思路如下：
 * <p>
 * 输入: [2,3,-2,4]
 * max: [2,6,-2,4]
 * min: [2,3,-12,-48]
 * @date 2020/12/21 10:38
 */
public class 难_乘积最大序列 {

    public static void main(String[] args) {
        System.out.println(xulie(new int[]{2, 3, -2, 4}));
    }

    public static int xulie(int[] nums) {
        if (null == nums || nums.length == 0) return -1;

        int[] maxDP = new int[nums.length];
        int[] minDP = new int[nums.length];
        maxDP[0] = minDP[0] = nums[0];

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxDP[i] = Math.max(Math.max(nums[i] * maxDP[i - 1], nums[i]), Math.max(nums[i] * minDP[i - 1], nums[i]));
            minDP[i] = Math.min(Math.min(nums[i] * maxDP[i - 1], nums[i]), Math.min(nums[i] * minDP[i - 1], nums[i]));
            result = Math.max(result, Math.max(maxDP[i], minDP[i]));
        }

        print(maxDP);
        print(minDP);

        return result;
    }

    public static void print(int[] dp) {
        for (int anInt : dp) {
            System.out.print(anInt + " ");
        }
        System.out.println();
    }

}
