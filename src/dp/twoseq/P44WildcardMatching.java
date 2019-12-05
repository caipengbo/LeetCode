package dp.twoseq;

/**
 * Title: 44. 通配符匹配
 * Desc: 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 *
 * Created by Myth-Lab on 11/29/2019
 */
public class P44WildcardMatching {

    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        // init dp[0][] dp[][0]
        for (int i = 1; i <= m; i++) {
            if (dp[0][i-1] && p.charAt(i-1) == '*') dp[0][i] = true;
        }
        for (int i = 0; i < n; i++) {  // s[0:i]
            for (int j = 0; j < m; j++) {  // p[0:j]
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') dp[i+1][j+1] = dp[i][j];
                else if (p.charAt(j) == '*') {
                    dp[i+1][j+1] = (dp[i][j] || dp[i+1][j] || dp[i][j+1]); // 匹配单个串，或者匹配多个串
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        P44WildcardMatching p44 = new P44WildcardMatching();
        System.out.println(p44.isMatch("aa", "a"));
        System.out.println(p44.isMatch("aa", "*"));
        System.out.println(p44.isMatch("ca", "?a"));
        System.out.println(p44.isMatch("adceb", "a*c?b"));
    }
}
