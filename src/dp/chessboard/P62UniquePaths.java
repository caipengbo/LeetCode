package dp.chessboard;

/**
* Title:  62. 不同路径(二维动态规划)
* Desc: 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
问总共有多少条不同的路径？
* Created by Myth on 12/05/2019 in VSCode
*/

public class P62UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        if (m < 1 || n < 1) return 0;
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1]; 
            }
        }
        return dp[m-1][n-1];
    }
}