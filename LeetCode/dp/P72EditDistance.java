package dp;

/**
 * Title: 72. 编辑距离
 * Desc:
 * Created by Myth-Lab on 11/27/2019
 */
public class P72EditDistance {
    //
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length(), i, j;
        int[][] dp = new int[n+1][m+1];
        for (i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= m; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
            }
        }
        return dp[n][m];
    }
}
