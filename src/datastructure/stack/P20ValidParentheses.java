package datastructure.stack;

import java.util.Stack;

/**
* Title: 20. 有效的括号
* Desc: ( )  [ ]  { }  
* Created by Myth-MBP on 15/03/2020 in VSCode
*/

public class P20ValidParentheses {
    
    public boolean isValid(String s) {
        if (s == null) return false;
        int n = s.length();
        // if (n == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) return false;
                char peek = stack.pop();
                if (!((peek == '(' && ch == ')') || (peek == '[' && ch == ']') || (peek == '{' && ch == '}'))) return false;
            } else if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}