package leetcode.回溯_递归;

/**
 * @author albertliu
 * @className 难_括号生产
 * @description 回溯法
 * 例如，给出 n =3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * @date 2020/12/22 16:28
 */
public class 难_括号生成 {

    public static void main(String[] args) {
        kuohao(3, 3, "");
    }

    private static void kuohao(int left, int right, String result) {
        //left > right是")("的情况
        if (left < 0 || right < 0 || left > right) return;

        if (left == 0 && right == 0) {
            System.out.println(result);
        }

        kuohao(left - 1, right, result + "(");
        kuohao(left, right - 1, result + ")");
    }

}
