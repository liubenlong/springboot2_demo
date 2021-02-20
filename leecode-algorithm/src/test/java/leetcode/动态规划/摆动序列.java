package leetcode.动态规划;

/**
 * 摆动序列
 *
 * 题目描述:
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列。
 * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 *
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
 * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。
 * 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 */
public class 摆动序列 {

    public static void main(String[] args) {
        System.out.println(baidong(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println(baidong(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        System.out.println(baidong(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    public static int baidong(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        dp[1] = 2;

        //拐点标记
        boolean flag = nums[1] - nums[0] > 0 ? true : false;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 0 == flag) {
                dp[i] = dp[i - 1];
            } else {
                //nums组成的曲线出现拐点。则dp[i]需要加1，然后反正flag标记
                dp[i] = dp[i - 1] + 1;
                flag = !flag;
            }
        }

        print(dp);
        return dp[nums.length - 1];

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


    public static void print(int[] dp) {
        for (int anInt : dp) {
            System.out.print(anInt + " ");
        }
        System.out.println();
    }

}
