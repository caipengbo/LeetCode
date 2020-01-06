package twopointer;

import java.util.Stack;

/**
* Title: 32. 最长有效括号(使用栈的解法package datastructure.stack)
* Desc: 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的 子串的长度。
* Created by Myth on 01/06/2020 in VSCode
*/

public class P32LongestValidParentheses {
    // 解法2
    public int longestValidParentheses(String s) {
        int left = 0, right = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (right == left) max = Math.max(max, 2*right);
            else if (right > left) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (right == left) max = Math.max(max, 2*left);
            else if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return max; 

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