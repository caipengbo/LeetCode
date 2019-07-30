package search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Title: 542. 01 矩阵
 * Desc: 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。两个相邻元素间的距离为 1 。
 * 输入:
 *      0 0 0
 *      0 1 0
 *      1 1 1
 * 输出:
 *      0 0 0
 *      0 1 0
 *      1 2 1
 * Created by Myth on 7/30/2019
 */
public class P542Matrix01 {
    // BFS
    // 我的思路（从不是0的地方考虑）：copy matrix，然后变量origin matrix，当前位置是0，就置为0，不是0 就一圈一圈的找0,
    // 优秀代码思路（从0开始扩展）：从0开始扩展，和 0 挨着的距离为 1，然后把和0挨着的往外接着扩展，依次距离+1
    private int[][] bfs(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    used[i][j] = true;
                }
            }
        }
        // 四个方向
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : directions) {
                int r = cell[0] + dir[0];
                int c = cell[1] + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n || used[r][c]) continue;
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1; // 周边元素+1
                used[r][c] = true;
                queue.offer(new int[]{r, c});
            }
        }
        return matrix;
    }
    public int[][] updateMatrix(int[][] matrix) {
        return bfs(matrix);
    }
}
