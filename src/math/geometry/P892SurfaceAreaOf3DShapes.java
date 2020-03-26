package math.geometry;

/**
* Title: 892. 三维形体的表面积
* Desc: 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
请你返回最终形体的表面积。
* Created by Myth-MBP on 25/03/2020 in VSCode
*/

public class P892SurfaceAreaOf3DShapes {
    // 本题不能用侧视图的做法去做，因为有挖空的
    // 思路：所有所有表面积-重叠的表面积
    public int surfaceArea(int[][] grid) {
        if (grid == null || grid.length == 0) return 0; 
        int n = grid.length, m = grid[0].length;
        int area = 0;
        // 一个柱子一个柱子的去看
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) continue;  // 跳过高度为0的
                area += grid[i][j] * 4 + 2;  // 每个柱子的表面积为 四个侧面+2个上下面
                // 重叠的部分：只数 左边和上边 较小的高度 * 2（乘以2就是加上右边和下面）
                if (i > 0) area -= Math.min(grid[i][j], grid[i-1][j]) * 2;
                if (j > 0) area -= Math.min(grid[i][j], grid[i][j-1]) * 2;
            }
        }
        return area;
    }
}