package search.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Title: 934. 最短的桥
 * Desc: 存在两座岛（岛是由1组成），找最短的桥，使得两个岛屿相连
 * Created by Myth on 7/30/2019
 */
public class P934ShortestBridge {
    // DFS + BFS
    // DFS: 将第一个岛屿标记为 数字 2（或者使用used 布尔数组）(将第一个岛屿的位置全部放在queue中供bfs使用)
    // BFS：从第一个岛屿 层级遍历到 第2个岛屿
    private void dfs(int[][] A, boolean[][] used, int i, int j, Queue<int[]> queue) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length || used[i][j] || A[i][j] != 1) return;
        used[i][j] = true;
        queue.add(new int[]{i, j});
        dfs(A, used, i-1, j, queue);
        dfs(A, used, i+1, j, queue);
        dfs(A, used, i, j-1, queue);
        dfs(A, used, i, j+1, queue);
    }
    private int bfs(int[][] A, boolean[][] used, Queue<int[]> queue) {
        int dis = -1;
        int size;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            dis++;
            size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] dir : directions) {
                    int r = cell[0] + dir[0];
                    int c = cell[1] + dir[1];
                    if (r < 0 || r >= A.length || c < 0 || c >= A[0].length || used[r][c]) continue;
                    if (A[r][c] == 1) return dis;
                    used[r][c] = true;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        return 0;
    }
    public int shortestBridge(int[][] A) {
        if (A.length == 0) return 0;
        int m = A.length, n = A[0].length;
        boolean[][] used = new boolean[m][n];
        boolean found = false;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (A[i][j] == 1) {
                    dfs(A, used, i, j, queue);
                    found = true;
                }
            }
        }

        return bfs(A, used, queue);
    }

    public static void main(String[] args) {
        P934ShortestBridge p934 = new P934ShortestBridge();
        int[][] A = {{0,1,0},{0,0,0},{0,0,1}};
        System.out.println(p934.shortestBridge(A));
    }

}
