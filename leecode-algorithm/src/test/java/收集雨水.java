import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://blog.csdn.net/asmartkiller/article/details/97375220
 * https://blog.csdn.net/yysave/article/details/92809515
 */
public class 收集雨水 {

    public class Cell {
        int row;
        int col;
        int height;

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        @Override
        public String toString() {
            return "{" + row + "," + col + "," + height + "}";
        }
    }

    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return 0;

        //PriorityQueue优先级队列，内部使用最小堆实现。这是本题的关键
        PriorityQueue<Cell> queue = new PriorityQueue<>(1, Comparator.comparingInt(a -> a.height));

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];

        // Initially, add all the Cells which are on borders to the queue.
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
            System.out.println(queue);
        }

        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(m - 1, i, heights[m - 1][i]));
            System.out.println(queue);
        }

        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
        // add all its neighbors to the queue.
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            System.out.println(queue);

            for (int[] dir : dirs) {
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    res += Math.max(0, cell.height - heights[row][col]);
                    queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));

                    System.out.println(queue);
                }
            }
        }

        return res;
    }

    /**
     * 二维数组收集雨水
     */
    @Test
    public void test() {
        int i = trapRainWater(new int[][]{
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        });
        System.out.println(i);
    }


    public int trap(int[] array) {
        int l = 0, r = array.length - 1, level = 0, res = 0;
        while (l < r) {
            int lower = array[(array[l] < array[r]) ? l++ : r--];
            level = Math.max(level, lower);
            res += level - lower;
        }
        return res;
    }

    /**
     * 一维数组收集雨水
     */
    @Test
    public void test1() {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}