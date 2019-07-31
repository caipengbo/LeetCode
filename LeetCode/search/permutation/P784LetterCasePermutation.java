package search.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 784 字母大小写全排列（这个题更像一个组合问题）
 * Desc: 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * Created by Myth on 7/19/2019
 */
public class P784LetterCasePermutation {
    private void backtracking(String S, int start, char[] cur, List<String> ans) {
        ans.add(new String(cur));
        for (int i = start; i < S.length(); i++) {
            if (S.charAt(i) >= 'a' && S.charAt(i) <= 'z') {
                cur[i] = (char) (cur[i] - 'a' + 'A');
                backtracking(S, i+1, cur, ans);
                cur[i] = (char) (cur[i] - 'A' + 'a');
            }
            if (S.charAt(i) >= 'A' && S.charAt(i) <= 'Z') {
                cur[i] = (char) (cur[i] - 'A' + 'a');
                backtracking(S, i+1, cur, ans);
                cur[i] = (char) (cur[i] - 'a' + 'A');
            }
        }
    }
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        backtracking(S, 0, S.toCharArray(), ans);
        return ans;
    }
}
