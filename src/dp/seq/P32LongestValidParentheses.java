package dp.seq;

import java.util.Stack;

/**
* Title: 32. 最长有效括号(DP 解法)
* Desc: 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的 子串的长度。
* Created by Myth on 01/06/2020 in VSCode
*/

public class P32LongestValidParentheses {
    // i-1和i为"()"情况 dp[i] = dp[i-2]+2      
    // i-1和i为 "))" 要看i-1位置的")" 匹配了多少, 且要看 i-dp[i-1]-1位置是否为 "(" 则 dp[i] = dp[i-1] + 2+ dp[i-dp[i-1]-1-1]
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }


    public static void main(String[] args) {
        P32LongestValidParentheses p32 = new P32LongestValidParentheses();
        System.out.println(p32.longestValidParentheses(""));
        System.out.println(p32.longestValidParentheses("("));
        System.out.println(p32.longestValidParentheses("()"));
        System.out.println(p32.longestValidParentheses(")("));
        System.out.println(p32.longestValidParentheses(")()())"));
    }
}