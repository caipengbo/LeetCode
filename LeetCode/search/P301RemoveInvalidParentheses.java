package search;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 301. 删除无效的括号(困难回溯)
 * Desc: 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。说明: 输入可能包含了除 ( 和 ) 以外的字符。
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/
 * Created by Myth on 7/23/2019
 */
public class P301RemoveInvalidParentheses {
    // 标准的回溯写法（简单写法，效率比较低）：统计不合法的左括号和右括号的数目，先删除右括号，再删除左括号，查看串是否valid

    // 如何判断有效： 统计左右括号的数目，右括号多的时候，就是出错的时候
    private void backtracking(String s, int iStart, int jStart, char openChar, char closeChar, List<String> ans) {
        int openNum = 0, closeNum = 0;
        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == openChar) openNum++;
            if (s.charAt(i) == closeChar) closeNum++;
            // 右括号多了，说明该串无效
            if (closeNum > openNum) {
                // 找出错的位置
                for (int j = jStart; j <=i ; j++) {
                    // 该判断条件是避免重复，()()), 有连续的括号，只删除第一个
                    if (s.charAt(j) == closeChar && (j == jStart || s.charAt(j-1) != closeChar)) {
                        // 删除出错的位置
                        String removed = s.substring(0, j) + s.substring(j + 1);
                        backtracking(removed, i, j, openChar, closeChar, ans);
                    }
                }
                return;
            }
        }
        // 整个串左括号大于右括号的情况: 翻转串，重复执行上面的步骤
        String reversed = new StringBuilder(s).reverse().toString();
        if (openChar == '(') {
            backtracking(reversed, 0, 0, ')', '(', ans);
        } else {
            ans.add(reversed);
        }

    }
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(s, 0, 0, '(', ')', ans);
        return ans;
    }
}
