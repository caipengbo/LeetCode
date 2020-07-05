package dp.chessboard;

/**
* Title: 64. 最小路径和
* Desc: 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
        说明：每次只能向下或者向右移动一步。
* Created by Myth on 12/06/2019 in VSCode
*/

public class P64MinimumPathSum {
    public int minPathSum(int[][] grid) {
        // m > 0, n > 0
        int m = grid.length, n = grid[0].length;
        if (m < 1 || n < 1) return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        
        for (int i = 1; i < m; i++) {  // 行
            for (int j = 1; j < n; j++) {   // 列
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]; 
            }
        }
        return dp[m-1][n-1];
    }
}