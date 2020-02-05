package datastructure.array;

import java.util.LinkedList;
import java.util.List;

/**
 * Title: 54. 螺旋矩阵 
 * Desc: 
 * Created by Myth-PC on 05/02/2020 in VSCode
 */
public class P54SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new LinkedList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return ret;
        int m = matrix.length, n = matrix[0].length, count = m*n;
        boolean[][] visited = new boolean[m][n];
        int i = 0, j = 0;
        int direction = 0;  // 右0  下1  左2  上3
        while (count > 0) {
            if (direction == 0) {
                if (j == n || visited[i][j]) {
                    i = i + 1;
                    direction = 1;
                    j = j - 1;
                } else {
                    ret.add(matrix[i][j]);
                    visited[i][j] = true;
                    j++;
                    count--;
                }
            } else if (direction == 1) {
                if (i == m || visited[i][j]) {
                    i = i - 1;
                    direction = 2;
                    j = j - 1;
                } else {
                    ret.add(matrix[i][j]);
                    visited[i][j] = true;
                    i++;
                    count--;
                }
            } else if (direction == 2) {
                if (j < 0 || visited[i][j]) {
                    j = j + 1;
                    direction = 3;
                    i = i - 1;
                } else {
                    ret.add(matrix[i][j]);
                    visited[i][j] = true;
                    j--;
                    count--;
                }
            } else if (direction == 3) {
                if (i < 0 || visited[i][j]) {
                    i = i + 1;
                    direction = 0;
                    j = j + 1;
                } else {
                    ret.add(matrix[i][j]);
                    visited[i][j] = true;
                    i--;
                    count--;
                }
            }
        }
        return ret;
    }

    // 不使用visited数组，设置边界
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ret = new LinkedList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return ret;
        int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1; 
        int i, j;
        while (left <= right && top <= bottom) {
            for (j = left; j <= right; j++) ret.add(matrix[top][j]);
            top++;
            if (top > bottom) break;
            for (i = top; i <= bottom; i++) ret.add(matrix[i][right]);
            right--;
            if (right < left) break;
            for (j = right; j >= left; j--) ret.add(matrix[bottom][j]);
            bottom--;
            if (top > bottom) break;
            for (i = bottom; i >= top; i--) ret.add(matrix[i][left]);
            left++;
        }
        return ret;
    }

}