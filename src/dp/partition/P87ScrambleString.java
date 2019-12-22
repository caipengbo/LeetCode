package dp.partition;

/**
* Title: 87. 扰乱字符串（划分型DP Hard）
* Desc: 
* Created by Myth on 12/22/2019 in VSCode
*/

public class P87ScrambleString {
    // S1（分成S11、S12）和S2（分成S21、S22）是 Scramble
    //     S11和S21是Scramble && S12和S22是Scramble
    // 或者 S11和S22是Scramble && S12和S21是Scramble
    // 这个思路可以用递归（记忆化搜索）
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int n = s1.length();
        if (n == 0) return true;
        // dp[i][j][k]：s1[i..i+k) 和 s2[j...j+k) 是否为scramble
        boolean[][][] dp = new boolean[n][n][n+1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i < n-len+1; i++) {
                for(int j = 0; j < n-len+1; j++) {
                    for(int k = 1; k < len; k++) {  // 划分
                        dp[i][j][len] |= (dp[i][j][k] && dp[i+k][j+k][len-k]) || (dp[i][j+len-k][k] && dp[i+k][j][len-k]);
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}