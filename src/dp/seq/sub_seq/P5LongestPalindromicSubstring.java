package dp.seq.sub_seq;

/**
* Title:  5. 最长的回文子串(DP + 中心扩散法)
* Desc: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
* Created by Myth on 12/28/2019 in VSCode
*/
public class P5LongestPalindromicSubstring {
    // dp[i,j] = dp[i+1, j-1] + 2   if s[i] == s[j]
    // 状态转移方程决定了填表的顺序
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0) return "";
        boolean[][] dp = new boolean[len][len];
        int longest = 1, start = 0;
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int i = len-1; i >= 0; i--) {
            for (int j = i+1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (i+1 > j-1 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    if (j-i+1 > longest) {
                        longest = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        // for (int i = 0; i < len; i++) {
        //     for (int j = 0; j < len; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return s.substring(start, start+longest);
    }
    public static void main(String[] args) {
        P5LongestPalindromicSubstring p5 = new P5LongestPalindromicSubstring();
        System.out.println(p5.longestPalindrome("abaabd"));
        System.out.println(p5.longestPalindrome("babad"));
        System.out.println(p5.longestPalindrome("aaaaa"));
        System.out.println(p5.longestPalindrome("cbbd"));
        System.out.println(p5.longestPalindrome("abcde"));
    }
}