package search;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 17. Letter Combinations of a Phone Number
 * Desc: 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 相关题目：39.
 * Created by Myth on 7/15/2019
 */
public class P17LetterCombinations {
    private String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    // DFS 递归函数
    // pos是位置，cur 是当前生成的串
    private void dfs(String digits, int pos, String cur, List<String> ans) {
        // dfs end
        if (pos >= digits.length()) {
            ans.add(cur);
            return;
        }
        // loop and recursive
        String letters = map[digits.charAt(pos)-'0'];
        for (int i = 0; i < letters.length(); i++) {
            // 注意此处
            // Error: cur = cur + letters.charAt(i);
            String newCur = cur + letters.charAt(i);
            dfs(digits, pos+1, newCur, ans);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (!"".equals(digits)) {
            dfs(digits, 0, "", ans);
        }
        return ans;
    }
    // BFS 非递归 把后面的追加到前面的解上面
    public List<String> bfs(String digits) {
        List<String> ans = new ArrayList<>();
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            String letters = map[digits.charAt(i)-'0'];
            List<String> tmp = new ArrayList<>();
            // 这种写法会得到和DFS一样的结果
//            for (String ele : ans) {
//                for (int j = 0; j < letters.length(); j++) {
//                    tmp.add(ele+letters.charAt(j));
//                }
//            }
            for (int j = 0; j < letters.length(); j++) {
                for (String ele : ans) {
                    tmp.add(ele+letters.charAt(j));
                }
            }

            ans = tmp;
        }
        return ans;
    }

    public static void main(String[] args) {
        P17LetterCombinations p17 = new P17LetterCombinations();
        // List<String> list = p17.letterCombinations("23");
        // DFS:["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
        // System.out.println(list.toString());
        List<String> list = p17.bfs("23");
        System.out.println(list.toString());

    }
}
