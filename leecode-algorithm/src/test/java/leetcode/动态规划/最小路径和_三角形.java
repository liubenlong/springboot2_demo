package leetcode.动态规划;

/**
 * @author albertliu
 * @className 三角形最小路径和
 * @description TODO
 * @date 2021/1/22 14:34
 */
public class 最小路径和_三角形 {
    public static void main(String[] args) {
        //三角形最小路径和
        int[][] nums = new int[][]{
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}};
        print(nums);

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = 0; j < nums[i].length; j++) {
                nums[i][j] = Math.min(nums[i+1][j], nums[i+1][j+1]) + nums[i][j];
            }
        }

        print(nums);
        System.out.println("最小路径和为" + nums[0][0]);
    }


    public static void print(int[][] dp) {
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
