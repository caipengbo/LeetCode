package search.bfs;

import java.util.Queue;

/**
 * Title: 994. 腐烂的橘子（广度优先遍历）
 * Desc:  和二叉树的层序遍历基本一样
 * Created by Myth-PC on 04/03/2020 in VSCode
 */
public class P994RottingOrange {
    public int orangesRotting(int[][] grid) {
        int count = bfs(grid);
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        return count;
    }
    private int bfs(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length, n = grid[0].length , i, j, k, count = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (grid[i][j] == 2) queue.add(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rot = false;
            for (k = 0; k < size; k++) {
                int[] cur = queue.poll();
                if (cur[0]-1 >= 0 && grid[cur[0]-1][cur[1]] == 1) {
                    grid[cur[0]-1][cur[1]] = 2;
                    queue.add(new int[] {cur[0]-1,cur[1]});
                    rot = true;
                }
                if (cur[0]+1 < m && grid[cur[0]+1][cur[1]] == 1) {
                    grid[cur[0]+1][cur[1]] = 2;
                    queue.add(new int[] {cur[0]+1,cur[1]});
                    rot = true;
                }
                if (cur[1]-1 >= 0 && grid[cur[0]][cur[1]-1] == 1) {
                    grid[cur[0]][cur[1]-1] = 2;
                    queue.add(new int[] {cur[0],cur[1]-1});
                    rot = true;
                }
                if (cur[1]+1 < n && grid[cur[0]][cur[1]+1] == 1) {
                    grid[cur[0]][cur[1]+1] = 2;
                    queue.add(new int[] {cur[0],cur[1]+1});
                    rot = true;
                }
            }
            if (rot) count++;
        }
        return count;
    }
}