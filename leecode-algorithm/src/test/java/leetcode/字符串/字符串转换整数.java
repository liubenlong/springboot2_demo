package leetcode.字符串;

/**
 * @author albertliu
 * @className 字符串转换整数
 * @description TODO
 * @date 2020/12/10 19:04
 */
public class 字符串转换整数 {
    public static void main(String[] args) {
        System.out.println(myAtoi("123"));
        System.out.println(myAtoi("123drgstd"));
        System.out.println(myAtoi("-sd5123ss"));
        System.out.println(myAtoi("sd5123ss"));
        System.out.println(myAtoi("  sd5123ss"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));


    }

    public static int myAtoi(String str) {
        str = str.trim();
        char c1 = str.charAt(0);
        if((c1 < 48 || c1>57) && c1 != '-'){
            return 0;
        }
        boolean b = c1 == '-';
        if (b) {
            str = str.substring(1);
        }

        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 48 && c <= 57) {
                if(Integer.MAX_VALUE - result * 10 < c - 48){
                    return b ? -1*Integer.MAX_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + c - 48;
            }
        }

        return b ? -1 * result : result;
    }
}
