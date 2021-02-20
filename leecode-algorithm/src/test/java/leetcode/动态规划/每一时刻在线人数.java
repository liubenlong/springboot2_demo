package leetcode.动态规划;

/**
 * 有一个日志文件，记录用户登录抖音、登出抖音的时间，求每一时刻在线人数
 * 格式为uid login_time logout_time
 *
 * 输入: logs = [[1, 0, 5], [2, 0, 6], [3, 0, 3],
 * [4, 1, 2], [5, 1, 3], [6, 2, 3],
 * [7, 3, 4], [8, 4, 6]]
 *
 * 输出: [3, 5, 5, 3, 3, 2, 0]
 */
public class 每一时刻在线人数 {
    public static void main(String[] args){
        int [][] nums = new int[][]{
                {1, 0, 5}, {2, 0, 6}, {3, 0, 3},
                 {4, 1, 2}, {5, 1, 3},
                {6, 2, 3}, {7, 3, 4}, {8, 4, 6}};

        //记录每个时刻登入登出的人数. 7是时间的最长刻度。
        int [] dpin = new int[7];
        int [] dpout = new int[7];
        //动态规划。dp[i] = d[i-1] + dpin[i] - dpout[i];
        int [] dp = new int[7];

        for(int i = 0; i < nums.length; i++){
            dpin[nums[i][1]] =  dpin[nums[i][1]]+1;
            dpout[nums[i][2]] =  dpout[nums[i][2]]+1;
        }

        print(dpin);
        print(dpout);

        //初始化第0个位置
        dp[0] = dpin[0] - dpout[0];

        for(int i = 1 ; i < dp.length;  i++){
            dp[i] = dp[i-1] + dpin[i] - dpout[i];
        }

        print(dp);
    }

    private static void print(int [] dp){
        for(int i = 0 ; i < dp.length;i++){
            System.out.print(dp[i]);
        }
        System.out.println();
    }

}
