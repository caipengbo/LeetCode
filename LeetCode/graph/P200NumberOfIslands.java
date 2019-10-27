package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Title: 200. 岛屿数量
 * Desc: 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * Created by Myth-Lab on 10/22/2019
 */
public class P200NumberOfIslands {
    // 并查集的写法见 第547题
    // Flood Fill问题
    // 遍历数组，找到为1 的地方，然后进行DFS，将Flood过的地方置为0，查看能找到几个独立的块儿
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    // bfs(grid, i, j);
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    // DFS
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }
    // BFS, 难点，下标的转换(可以直接使用数组，不使用坐标的转换)
    private void bfs(char[][] grid, int i, int j) {
        int rowNum = grid.length, colNum = grid[0].length;
        grid[i][j] = '0';
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i * colNum + j);
        while (!queue.isEmpty()) {
            int id = queue.remove();
            int row = id / colNum;
            int col = id % colNum;
            if (row - 1 >= 0 && grid[row-1][col] == '1') {
                queue.add((row-1) * colNum + col);
                grid[row-1][col] = '0';
            }
            if (row + 1 < rowNum && grid[row+1][col] == '1') {
                queue.add((row+1) * colNum + col);
                grid[row+1][col] = '0';
            }
            if (col - 1 >= 0 && grid[row][col-1] == '1') {
                queue.add(row * colNum + col-1);
                grid[row][col-1] = '0';
            }
            if (col + 1 < colNum && grid[row][col+1] == '1') {
                queue.add(row * colNum + col+1);
                grid[row][col+1] = '0';
            }
        }
    }
}
