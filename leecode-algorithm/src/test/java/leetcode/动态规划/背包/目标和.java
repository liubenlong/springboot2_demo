package leetcode.动态规划.背包;

/**
 * 背包问题--目标和
 * <p>
 * 给定一个非负整数数组，a1, a2, …, an, 和一个目标数，S。
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 示例 1:
 * <p>
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * https://blog.csdn.net/weixin_42269817/article/details/107998587
 */
public class 目标和 {

    static int count = 0;

    public static void main(String[] args) {
        int[] nums = {2, 1, 1, 1};
        int target = 3;
        System.out.println(findTargetSumWays(nums, target));
        System.out.println(findTargetSumWaysDP(nums, target));
        System.out.println(findTargetSumWaysDP2(nums, target));
    }

    /**
     * 方法二： DP
     * 该问题可以转换为 Subset Sum 问题，从而使用 0-1 背包的方法来求解。
     * 可以将这组数看成两部分，P 和 N，其中 P 使用正号，N 使用负号，有以下推导：
     *
     *                   sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     *                        2 * sum(P) = target + sum(nums)
     *                            sum(P) = (target + sum(nums)) / 2
     *
     * 上面是故意凑数sum(nums) , 便于计算
     *
     * @param nums
     * @param S
     * @return
     */
    public static int findTargetSumWaysDP(int[] nums, int S) {
        int size = nums.length;
        int sum = calculateSum(nums);
        if (S > sum || ((S + sum) & 1) == 1) {
            return 0;
        }
        int target = (S + sum)/ 2;// 原因参考上面注释的分析
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 0; i < size; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
            print(dp);
        }
        return dp[target];
    }

    public static int calculateSum(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return sum;
    }

    //方法三：两位数组DP
    static int findTargetSumWaysDP2(int[] nums, int S) {
        int sum = 0;
        for (int c : nums) {
            sum += c;
        }
        if (sum < S || (sum + S) % 2 == 1) return 0;
        int w = (sum + S) / 2;
        int n = nums.length;

        int[][] dp = new int[n + 1][w + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j <= w; j++) {
                if (j - nums[i - 1] < 0) {
                    //表示当前容量j装不下
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
            print(dp);
        }
        return dp[n][w];
    }


    //方法一：递归
    public static int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        calculate(nums, S, 0, sum);
        return count;
    }

    public static void calculate(int[] nums, int S, int i, int sum) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            } else {
                return;
            }
        } else {
            calculate(nums, S, i + 1, sum + nums[i]);
            calculate(nums, S, i + 1, sum - nums[i]);
        }
    }


    public static void print(boolean[][] dp) {
        for (boolean[] ints : dp) {
            for (boolean anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void print(boolean[] dp) {
        for (boolean anInt : dp) {
            System.out.print(anInt + " ");
        }
        System.out.println();
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
