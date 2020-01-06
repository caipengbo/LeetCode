package datastructure.stack;

import java.util.Stack;

/**
* Title: 32. 最长有效括号
* Desc: 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的 子串的长度。
* Created by Myth on 01/06/2020 in VSCode
*/

public class P32LongestValidParentheses {
    // 难点：括号有效
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.empty()) {
                    max = Math.max(max, i-stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return max;
    }
    // 解法2
    public int longestValidParentheses2(String s) {
        
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