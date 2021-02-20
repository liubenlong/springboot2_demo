package leetcode;

/**
 * @author albertliu
 * @className 三角形最小路径和
 * @description https://www.cnblogs.com/du001011/p/10903023.html
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 思想：从最底层向上找最短路径，下面给出两种解法，一维数组和二维数组两种解法，但是思想都是一样的。
 *
 * @date 2020/12/21 10:25
 */
public class 三角形最小路径和 {
    public static void main(String[] args) {
        System.out.println(lujing(new int[][]{}));
        System.out.println(lujing(new int[][]{{2}}));
        System.out.println(lujing(new int[][]{{2}, {3, 4}}));
        System.out.println(lujing(new int[][]{{2}, {3, 4}, {6, 5, 7}}));
        System.out.println(lujing(new int[][]{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}}));
    }


    public static int lujing(int[][] nums) {
        if (nums.length == 0) return -1;

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = 0; j < nums[i].length; j++) {
                nums[i][j] = nums[i][j] + Math.min(nums[i + 1][j], nums[i + 1][j + 1]);
            }
        }
        return nums[0][0];
    }
}
