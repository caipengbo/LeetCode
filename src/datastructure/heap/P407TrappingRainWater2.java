package datastructure.heap;

import java.util.PriorityQueue;

public class P407TrappingRainWater2 {

    public int trapRainWater(int[][] heightMap) {
        // 从外圈逐步的往内圈收缩
        // 使用小顶堆存储圈的最小值（木桶原理）
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int n = heightMap.length, m = heightMap[0].length;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> (o1[2] - o2[2]));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n-1 || j == m-1) {
                    minHeap.add(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        // 这个方向的坐标
        int[] direct = {-1, 0, 1, 0, -1};
        int sum = 0;
        while (!minHeap.isEmpty()) {
            int[] peek = minHeap.poll();
            // 四个方向
            for (int k = 0; k < 4; k++) {
                int x = peek[0] + direct[k];
                int y = peek[1] + direct[k+1];
                if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]) {
                    if (heightMap[x][y] < peek[2]) {
                        sum += peek[2] - heightMap[x][y];
                    }
                    minHeap.add(new int[]{x, y, Math.max(heightMap[x][y], peek[2])});
                    visited[x][y] = true;
                }
            }
        }
        return sum;
    }
}