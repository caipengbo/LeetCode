package dp.twoseq;

/**
 * Title: 115. 不同的子序列
 * Desc: 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 * 重点是S中以哪个字符结尾！！
 * Created by Myth-Lab on 12/2/2019
 */
public class P115DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        if (m > n) return 0;
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m && j <= i; j++) {
                if (s.charAt(i-1) != t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 不以S的当前字符为结尾 + 以S的当前字符结尾
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                }
            }
        }
        return dp[n][m];
    }
}
