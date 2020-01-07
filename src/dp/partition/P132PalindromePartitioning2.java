package dp.partition;

import java.util.Arrays;

/**
 * Title: 132. 分割回文串 II 
 * Desc: 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 返回符合要求的最少分割次数
 * Created by Myth on 01/07/2020 in VSCode
 */

public class P132PalindromePartitioning2 { 
    // 为什么n == 2 的时候需要单独讨论？？
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        // Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {  // 初始状态
            dp[i] = i;
        }
        // dp[0] = 0;
        // if (n >= 2) {
        //     dp[1] = (s.charAt(0) == s.charAt(1) ? 0 : 1);
        // }
        // dp[i]  s[j+1, ... ,i]
        for (int i = 1; i < n; i++) {
            if (isPalindrome(s, 0, i)) {
                dp[i] = 0;
            } else {
                for (int j = 1; j <= i; j++) {
                    if (isPalindrome(s, j, i)) dp[i] = Math.min(dp[i], dp[j-1]+1);
                }
                // for (int j = 0; j < i; j++) {
                //     if (isPalindrome(s, j+1, i)) dp[i] = Math.min(dp[i], dp[j]+1);
                // }
            }
        }
        return dp[n-1];
    }
    

    public boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // 使用 第5题 DP 保存 Palindrome
    public int minCut2(String s) {
        int n = s.length();
        int[] dp = new int[n];
        // Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {  // 初始状态
            dp[i] = i;
        }
        // 是否是回文串
        boolean[][] ispalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            ispalindrome[i][i] = true;
        }
        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (i+1 > j-1 || ispalindrome[i+1][j-1])) {
                    ispalindrome[i][j] = true;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if (ispalindrome[0][i]) {
                dp[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (ispalindrome[j+1][i]) dp[i] = Math.min(dp[i], dp[j]+1);
                }
            }
        }
        return dp[n-1];
    }
    
    public static void main(String[] args) {
        P132PalindromePartitioning2 p132 = new P132PalindromePartitioning2();
        System.out.println(p132.minCut("let"));
    }
}