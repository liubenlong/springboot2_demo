package leetcode.字符串;

/**
 * @author albertliu
 * @className 同构字符串
 * @description https://blog.csdn.net/weixin_40673608/article/details/86517277
 *
 * 输入: s = "paper",
 *      t = "title"
 * 输出: true
 *
 * 你可以假设 s 和 t 具有相同的长度。
 *
 * @date 2020/12/23 17:30
 */
public class 同构字符串 {

    public static void main(String[] args) {
        System.out.println(isIsomorphic("papzr", "title"));
    }


    private static boolean isIsomorphic(String a, String b) {
        //这两个数组用于记录字符上一次出现的位置。
        //然后逐个比较，只有对应位置上的字符不一样，那么返回false。
        int[] m = new int[127], n = new int[127];

        for (int i = 0; i < a.length(); i++) {
            if (m[a.charAt(i)] != n[b.charAt(i)]) {
                return false;
            } else {
                //这里+1  是因为初始化都是0，记录位置不可以为0，否则会出错
                m[a.charAt(i)] = i + 1;
                n[b.charAt(i)] = i + 1;
            }
        }
        return true;
    }

}
