package leetcode.动态规划;

/**
 * 比特位计数
 * 对于求解一个十进制数转化为二进制时里面1的个数，可以先看一下概况：
 * <p>
 * 十进制数　　　　　　　　二进制数　　　　　　　　1的个数
 * 　　1　　　　　　　　　　　　1　　　　　　　　　　   1
 * 　　2　　　　　　　　　　　　10　　　　　　　　　　 1
 * 　　3　　　　　　　　　　　　11　　　　　　　　　　  2
 * 　　4　　　　　　　　　　　　100　　　　　　　　　　1
 * 　　5　　　　　　　　　　　　101　　　　　　　　　　 2
 * 　　6　　　　　　　　　　　　110　　　　　　　　　　  2
 * 　　7　　　　　　　　　　　　111　　　　　　　　　　  3
 */
public class 难_比特位计数 {

    public static void main(String[] args) {
        print(dp(2));
        print(dp(5));

        print(dp2(2));
        print(dp2(5));
    }

    //方法一
    public static int[] dp(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i / 2] + i % 2;//偶数和奇数时不同
        }
        return dp;
    }

    //方法二
    public static int[] dp2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i & (i - 1)] + 1;//不是那么好理解
        }
        return dp;
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
