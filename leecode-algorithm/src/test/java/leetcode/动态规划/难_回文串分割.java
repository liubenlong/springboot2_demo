package leetcode.动态规划;

import java.util.*;

/**
 * 最少多少次分割，可以让分割后的字串都是回文串
 * https://b23.tv/cfAp9w
 */
public class 难_回文串分割 {

    public static void main(String[] args) {
        //是否是回文串
//        System.out.println(huiwen("a"));
//        System.out.println(huiwen("aa"));
//        System.out.println(huiwen("ab"));
//        System.out.println(huiwen("aba"));
//        System.out.println(huiwen("abc"));
//        System.out.println(huiwen("abba"));


        //最少多少次分割，可以让分割后的字串都是回文串
        String str = "mammal";
        int length = str.length();
        int[][] dp = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (huiwen(str.substring(i, j + 1))) {
                    dp[i][j] = 1;
                }
            }
        }

        print(dp);

        int[][] splitDP = new int[length][length];
        for (int l = 1; l <= length; l++) {//第一层循环：长度
            for (int j = 0; j + l <= length; j++) {//第二层循环：水平后移。也可以理解为Y轴区间
                if (l == 1) {//对角线不需要切分；单字母都是回文串
                    dp[l - 1][l - 1] = 0;
                } else if (dp[j][j + l - 1] == 1) {//本身是回文串，则不需要切分
                    splitDP[j][j + l - 1] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int m = 0; m < l - 1; m++) {//回文串每个间隙切分，统计最小切分数
                        //dp公式
                        int temp = splitDP[j][j + m] + splitDP[j + m + 1][j + l - 1] + 1;
                        min = temp < min ? temp : min;
                    }
                    splitDP[j][j + l - 1] = min;
                }
            }
            print(splitDP);
        }
        System.out.println("最少需要切分多少次："+splitDP[0][length-1]);
    }

    /**
     * 判断是不是回文串
     *
     * @param str
     * @return
     */
    public static boolean huiwen(String str) {
        if (null == str || "".equals(str)) return false;
        if (str.length() == 1) return true;

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) return false;
        }
        return true;

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

}
