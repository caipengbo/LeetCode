package datastructure.stack;

import java.util.Stack;

/**
* Title: 20. 有效的括号
* Desc: ( )  [ ]  { }  
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
有效字符串需满足：
1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。
3. 注意空字符串可被认为是有效字符串。
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