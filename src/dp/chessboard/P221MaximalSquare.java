package dp.chessboard;

/**
* Title: 221. 最大正方形（见第85题）
* Desc: 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
* Created by Myth on 12/07/2019 in VSCode
*/

public class P221MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        // dp中存储的是边长
        int[][] dp = new int[m][n];
        int max = 0;
        // 注意初始化
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') dp[i][0] = 1;  
            max = Math.max(max, dp[i][0]);
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') dp[0][i] = 1;
            max = Math.max(max, dp[0][i]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        return max*max;
    }
}