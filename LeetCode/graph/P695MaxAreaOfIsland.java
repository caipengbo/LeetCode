package graph;

/**
 * Title: 695. 岛屿的最大面积
 * Desc: 200题的改编题
 * Created by Myth-Lab on 10/22/2019
 */
public class P695MaxAreaOfIsland {
    // 改写自 P200
    private int count;
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int maxCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count = 0;
                    dfs(grid, i, j);
                    maxCount = Math.max(maxCount, count);
                }
            }
        }
        return maxCount;
    }
    // DFS
    private void dfs(int[][] grid, int i, int j) {
        if (grid.length == 0 || grid[0].length == 0) return;
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) return;
        grid[i][j] = 0;
        count++;
        // 在此处判断，不用进入下一层递归
        if (i-1 >= 0 && grid[i-1][j] == 1) dfs(grid, i-1, j);
        if (i+1 < m && grid[i+1][j] == 1) dfs(grid, i+1, j);
        if (j-1 >= 0 && grid[i][j-1] == 1) dfs(grid, i, j-1);
        if (j+1 < n && grid[i][j+1] == 1) dfs(grid, i, j+1);
    }
}
