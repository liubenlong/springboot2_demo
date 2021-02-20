package leetcode.动态规划;

/**
 * @author albertliu
 * @className 打家劫舍
 * @description https://blog.csdn.net/weixin_40673608/article/details/86512916
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * @date 2020/12/20 17:30
 */
public class 打家劫舍 {
    public static void main(String[] args) {
//        System.out.println(dajie(new int[]{2}));
//        System.out.println(dajie(new int[]{2, 7}));
//        System.out.println(dajie(new int[]{2, 7, 9}));
        System.out.println(dajie(new int[]{2, 7, 9, 3, 1}));
    }


    public static int dajie(int[] n) {
        if (n.length == 0) return 0;
        if (n.length == 1) return n[0];
        if (n.length == 2) return Math.max(n[0], n[1]);

        int[] dp = new int[n.length];
        dp[0] = n[0];
        dp[1] = Math.max(n[0], n[1]);
        for (int i = 2; i < n.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + n[i]);
            print(dp);
        }
        return dp[n.length - 1];
    }

    public static void print(int[] n) {
        for (int i : n) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }
}
