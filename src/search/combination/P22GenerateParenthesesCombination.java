package search.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 22. 括号生成
 * Desc: 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * https://leetcode-cn.com/problems/generate-parentheses/
 * Created by Myth on 7/23/2019
 */
public class P22GenerateParenthesesCombination {
    // 回溯(不带循环的回溯，因为每种选择不太一样，无法使用循环语句写出)
    // 结束：串的长度=2*n
    // 限制条件：左括号小于n时都可以放； 右括号小于左括号数目时才可以放
    private void backtracking(int n, int open, int close, String cur, List<String> ans) {
        if (cur.length() == 2*n) {
            // 不用new String，因为String每次会产生新的对象
            ans.add(cur);
            return;
        }
        if (open < n) {
            // 注意 cur+"(" 会产生新的对象
            backtracking(n, open+1, close, cur+"(", ans);
        }
        if (close < open) {
            backtracking(n, open, close+1, cur+")", ans);
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtracking(n, 0, 0, "", ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P22GenerateParenthesesCombination().generateParenthesis(2));
    }
}
