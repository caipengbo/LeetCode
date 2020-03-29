package search.bfs;

import java.util.LinkedList;
import java.util.Queue;


/**
* Title: 1162. 地图分析（多源广度优先搜索）
* Desc: 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。
其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远(何为最远？？？）的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。

我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
如果我们的地图上只有陆地或者海洋，请返回 -1。
* Created by Myth-MBP on 29/03/2020 in VSCode
*/

public class P1162FarLand {
    // ============= Error 理解错误题目！！！： 求的是每个海洋到每个陆地的距离之和，实际上应该求海洋到陆地的最近距离，让这个最近距离最远！！！！
    // BFS的时候保存两个数字，min(到最近的陆地距离) sum(到所有陆地的距离和)
    public int maxDistanceError(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        int[][][] dst = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    helperError(i, j, m, n, grid, dst);
                }
            }
        }
        int max = 0, min = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (max < dst[i][j][0]) {
                    max = dst[i][j][0];
                    min = dst[i][j][1];
                }
            }
        }
        return min;
    }
    // 难点不知道如何使用BFS，如何扩展节点?
    private void helperError(int i, int j, int m, int n, int[][] grid, int[][][] dst) {
        for (int p = 0; p < m; p++) {
            for (int q = 0; q < n; q++) {
                if (grid[p][q] == 0)  {
                    int d = Math.abs(p-i) + Math.abs(q-j);
                    dst[p][q][0] += d;
                    if (dst[p][q][1] == 0 || dst[p][q][1] > d) dst[p][q][1] = d;
                }
            }
        }
    }
    // =============== 正确解法 ================
    // 多源广度优先搜索
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        // grid==2 已经被访问
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) queue.add(new int[] {i, j});
            }
        }
        int count = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0]-1 >=0 && grid[cur[0]-1][cur[1]] == 0) {
                    grid[cur[0]-1][cur[1]] = 2;
                    queue.add(new int[] {cur[0]-1, cur[1]});
                }
                if (cur[0]+1 < m && grid[cur[0]+1][cur[1]] == 0) {
                    grid[cur[0]+1][cur[1]] = 2;
                    queue.add(new int[] {cur[0]+1, cur[1]});
                }
                if (cur[1]-1 >= 0 && grid[cur[0]][cur[1]-1] == 0) {
                    grid[cur[0]][cur[1]-1] = 2;
                    queue.add(new int[] {cur[0], cur[1]-1});
                }
                if (cur[1]+1 < n && grid[cur[0]][cur[1]+1] == 0) {
                    grid[cur[0]][cur[1]+1] = 2;
                    queue.add(new int[] {cur[0], cur[1]+1});
                }
            }
        }
        return count <= 0 ? -1 : count;
    }

    public static void main(String[] args) {
        P1162FarLand p1162 = new P1162FarLand();
        int[][] grid = {{1,0,0},{0,0,0},{0,0,0}};
        System.out.println(p1162.maxDistance(grid));
    }
}