package graph.unionfind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Title: 733. 图像渲染
 * Desc: 相连的同颜色上色，最后返回经过上色渲染后的图像。
 * Created by Myth-Lab on 10/23/2019
 */
public class P733FloodFill {
    // DFS  BFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) return image;
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    private void dfs(int[][] image, int i, int j, int oldColor, int newColor) {
        int m = image.length, n = image[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] != oldColor || image[i][j] == newColor) return;
        image[i][j] = newColor;
        dfs(image, i-1, j, oldColor, newColor);
        dfs(image, i+1, j, oldColor, newColor);
        dfs(image, i, j-1, oldColor, newColor);
        dfs(image, i, j+1, oldColor, newColor);
    }

    private int[][] bfs(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0 || image[sr][sc] == newColor) return image;
        // 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
        int m = image.length, n = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int oldColor = image[sr][sc];
        queue.add(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1];
            image[i][j] = newColor;
            if (i-1 >= 0 && image[i-1][j] == oldColor) queue.add(new int[] {i-1, j});
            if (i+1 < m && image[i+1][j] == oldColor) queue.add(new int[] {i+1, j});
            if (j-1 >= 0 && image[i][j-1] == oldColor) queue.add(new int[] {i, j-1});
            if (j+1 < n && image[i][j+1] == oldColor) queue.add(new int[] {i, j+1});
        }
        return image;
    }
}
