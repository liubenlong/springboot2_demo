package leetcode.动态规划;

public class 最大正方形 {

    //最大正方形
    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };


        int[][] dp = new int[nums.length][nums[0].length];

        int maxBianChang = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                //计算正方形边长
                if (i == 0 || j == 0) {
                    dp[i][j] = nums[i][j];
                } else if (nums[i][j] == 1) {//只有原数组中是1，才需要计算边长
                    //当前正方形边长 = min(左侧，上侧，左上侧) + 1
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }

                maxBianChang = Math.max(maxBianChang, dp[i][j]);
            }
        }
        print(dp);

        //输出面积
        System.out.println(maxBianChang * maxBianChang);
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
