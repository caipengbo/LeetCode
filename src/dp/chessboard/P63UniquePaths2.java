package dp.chessboard;

/**
* Title: 63. 不同路径 II
* Desc:  一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
* Created by Myth on 12/05/2019 in VSCode
*/

public class P63UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // m > 0, n > 0
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (m < 1 || n < 1) return 0;
        int[][] dp = new int[m][n];
        if(obstacleGrid[0][0] != 1) dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] != 1 && dp[i-1][0] != 0) dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] != 1 && dp[0][i-1] != 0) dp[0][i] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]; 
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m-1][n-1];
    }
}