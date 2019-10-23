package graph;

import util.IOUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Title: 827. 最大人工岛
 * Desc: 在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。
 *
 * 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）
 * Created by Myth-Lab on 10/23/2019
 */
public class P827MakingALargeIsland {
    // 染色法：对于每一个小岛都编号index(>1的数字; 我们从index 2开始，使用数组area[]记录面积)
    // 接着，对于每一个0，我们找到其四周index不同的小岛，然后相加，就可以获得最大的面积啦
    private int curIslandArea;
    public int largestIsland(int[][] grid) {
        // 1 <= grid.length = grid[0].length <= 50
        int m = grid.length, n = grid[0].length;
        int[] area = new int[250];
        int index = 2, maxIslandArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                curIslandArea = 0;
                dfs(grid, i, j, index);
                if (curIslandArea != 0) {
                    // 岛屿的最大面积
                    maxIslandArea = Math.max(maxIslandArea, curIslandArea);
                    // 记录每一块岛屿的面积
                    area[index++] = curIslandArea;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    maxIslandArea = Math.max(maxIslandArea, changeSea(grid, m, n, area, i, j));
                }
            }
        }
        return maxIslandArea;
    }
    // DFS 求每一个小岛，并且获得每一个小岛的面积
    private void dfs(int[][] grid, int i, int j, int index) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) return;
        grid[i][j] = index;
        curIslandArea++;
        // 在此处判断，不用进入下一层递归
        dfs(grid, i-1, j, index);
        dfs(grid, i+1, j, index);
        dfs(grid, i, j-1, index);
        dfs(grid, i, j+1, index);
    }
    // 尝试改变每一个海洋(为0的地方)
    private int changeSea(int[][] grid, int m, int n, int[] area, int i, int j) {
        Set<Integer> set = new HashSet<>();
        int areaSum = 1;
        if (i-1 >= 0) set.add(grid[i-1][j]);
        if (i+1 < m) set.add(grid[i+1][j]);
        if (j-1 >= 0) set.add(grid[i][j-1]);
        if (j+1 < n) set.add(grid[i][j+1]);
        for (Integer index : set) {
            areaSum += area[index];
        }
        return areaSum;
    }

    public static void main(String[] args) {
        P827MakingALargeIsland p827 = new P827MakingALargeIsland();
        int[][] grid1 = {{1, 0}, {0, 1}};
        int[][] grid2 = {{1,0,1,0,1}, {1,0,1,0,1}, {1,1,1,0,0}};
        int[][] grid3 = {{1, 1}, {1, 1}};
        System.out.println(p827.largestIsland(grid3));
    }
}
