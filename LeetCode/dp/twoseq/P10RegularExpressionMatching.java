package dp.twoseq;

/**
 * Title: 10.正则表达式匹配
 * Desc: 模式 '.' 匹配任意单个字符 ；'*' 匹配零个或多个前面的那一个元素（指的是模式串中的）
 * Created by Myth-Lab on 11/28/2019
 */
public class P10RegularExpressionMatching {
    /*
     * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
     * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
     * 3, If p.charAt(j) == '*':
     *    here are two sub conditions:
     *                1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
     *                2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
     *                               dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
     *                            or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
     *                            or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     */
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        int i, j;
        for (i = 0; i < m; i++) {
            // i == 0 是不可能为 *
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        // 遍历两个串
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') dp[i+1][j+1] = dp[i][j];
                else if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[n][m];
    }
}
