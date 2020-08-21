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
    // 打印公共子序列的路径
    public static String longestCommonSubsequence2(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n+1][m+1];
        int[][] path = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    path[i][j] = 0;
                } else if (dp[i-1][j] > dp[i][j-1]) {
                    dp[i][j] = dp[i-1][j];
                    path[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j-1];
                    path[i][j] = 2;
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        dfs(path, text1, n, m, ret);
        return ret.toString();
    }
    private static void dfs(int[][] path, String text1, int i, int j, StringBuilder cur) {
        if (i == 0 || j == 0) {
            return;
        }
        if (path[i][j] == 0) {
            dfs(path, text1, i-1, j-1, cur);
            cur.append(text1.charAt(i-1));
        } else if (path[i][j] == 1) {
            dfs(path, text1, i-1, j, cur);
        } else {
            dfs(path, text1, i, j-1, cur);
        }
    }
    public static void main(String[] args) {

        System.out.println(P1143LongestCommonSubsequence.longestCommonSubsequence2("a1b2d3eee4", "oo1a2cs3csd4"));

    }
}
