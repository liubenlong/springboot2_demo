package DP;

import org.junit.Test;

import java.util.Arrays;

public class 不同路径 {

    /**
     * 【Leetcode】62. 不同路径
     * https://segmentfault.com/a/1190000016315625
     */
    @Test
    public void test2() {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
        System.out.println(uniquePaths1(m, n));
    }


    private int uniquePaths(int m, int n) {
        int[][] ways = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) ways[i][j] = 1;
                else ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
            }
        }
        return ways[m - 1][n - 1];
    }


    public int uniquePaths1(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[j] += res[j - 1];
                System.out.println("i=" + i + "_" + "j=" + j + ":" + Arrays.toString(res));
            }
        }
        return res[n - 1];
    }
}
