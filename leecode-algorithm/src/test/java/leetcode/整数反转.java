package leetcode;

/**
 * @author albertliu
 * @className 整数反转
 * @description TODO
 * @date 2020/12/10 11:57
 */
public class 整数反转 {

    public static void main(String[] args) {
        System.out.println(reverse(234));
        System.out.println(reverse(-234));
        System.out.println(reverse(-3));
        System.out.println(reverse(-333));
        System.out.println(reverse(533));
        System.out.println(reverse(0));
    }

    public static String reverse(int x) {
        if (x == 0) return "0";

        int result = 0;
        int flag = 0;//0:正数
        if (x < 0) {
            x = Math.abs(x);
            flag = 1;
        }

        while (x > 0) {
            int yushu = x % 10;
            result = result * 10 + yushu;
            x = x / 10;
        }

        return (flag == 1 ? "-" : "") + result;
    }
}
