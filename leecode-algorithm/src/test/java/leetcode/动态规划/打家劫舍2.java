package leetcode.动态规划;

/**
 * @author albertliu
 * @className 打家劫舍
 * @description https://blog.csdn.net/qq_24094489/article/details/95973939?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-3&spm=1001.2101.3001.4242
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 分析：
 * * 由于最后一个和第一个是连在一起的，所以要么取第一个，要么取最后一个，只能2选1
 * * 所以我们只需要分别计算不含第1个元素的数组的最大值，和不含最后一个元素的数组的最大值，返回其中大的那个即可
 * @date 2020/12/20 17:30
 */
public class 打家劫舍2 {
    public static void main(String[] args) {
        System.out.println(dajie(new int[]{2}));
        System.out.println(dajie(new int[]{2, 7}));
        System.out.println(dajie(new int[]{2, 7, 9}));
        System.out.println(dajie(new int[]{2, 7, 9, 3, 1}));
    }


    public static int dajie(int[] n) {
        if (n.length == 0) return 0;
        if (n.length == 1) return n[0];
        if (n.length == 2) return Math.max(n[0], n[1]);

        return Math.max(aaaaa(n, 0, n.length - 1), aaaaa(n, 1, n.length));
    }

    private static int aaaaa(int[] n, int start, int end) {
        int[] dp = new int[n.length];
        dp[start] = n[start];
        dp[start + 1] = Math.max(n[start], n[start + 1]);
        for (int i = start + 2; i < end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + n[i]);
//            print(dp);
        }
        return dp[end - 1];
    }

    public static void print(int[] n) {
        for (int i : n) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }
}
