package leetcode.回溯_递归;

/**
 * @author albertliu
 * @className 单词搜索
 * @description 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 * @date 2020/12/24 14:29
 */
public class 难_单词搜索 {

    public static void main(String[] args) {
        Character[][] board = new Character[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};

        System.out.println(find(board, "ABCCEE"));
        System.out.println(find(board, "VV"));
        System.out.println(find(board, "ABCCED"));
        System.out.println(find(board, "SEE"));
        System.out.println(find(board, "ABCB"));
    }

    private static boolean find(Character[][] board, String word) {

        boolean b = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    //递归寻找上下左右四个方向
                    b = b || find(board, word, i, j, 0);
                }
            }
        }

        return b;
    }


    /**
     * fangxiang
     * 不可以走回头路，通过fangxaing控制
     * 1：上；2：右；3：下；4：左
     * @return
     */
    private static boolean find(Character[][] board, String word, int i, int j, int fangxiang) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (null == word || "".equals(word)) return true;
        if (word.charAt(0) != board[i][j]) return false;

        String substring = word.substring(1);

        return (fangxiang == 3 ? false : find(board, substring, i - 1, j, 1)) ||
                (fangxiang == 4 ? false : find(board, substring, i, j + 1, 2)) ||
                (fangxiang == 1 ? false : find(board, substring, i + 1, j, 3)) ||
                (fangxiang == 2 ? false : find(board, substring, i, j - 1, 4));
    }

}
