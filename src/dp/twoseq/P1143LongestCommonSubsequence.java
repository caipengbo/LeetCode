package dp.twoseq;

/**
 * Title: 1143. 最长公共子序列（最长公共子串 718题）
 * Desc: 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 * Created by Myth-Lab on 12/3/2019
 */
public class P1143LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[n][m];
    }
}
