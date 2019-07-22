package search;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 131. 分割回文串
 * Desc: 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。返回 s 所有可能的分割方案。
 * Created by Myth on 7/21/2019
 */
public class P131PalindromePartitioning {
    // 典型的回溯分割
    // 循环：i 从1 到 len(最大分割)，代表分割0个-len 个
    // 剪枝：待分割的串长度 小于 len
    // 结束：start == len
    // 分割：从start往后分割
    private void backtracking(String s, int start, List<String> cur, List<List<String>> ans) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i <= s.length(); i++) {
            if (start+i > s.length()) break;
            String segment = s.substring(start, start+i);
            if (isPalindrome(segment)) {
                cur.add(segment);
                backtracking(s, start+i, cur, ans);
                cur.remove(cur.size()-1);
            }
        }
    }
    // 是否为回文串：正读反读都一样
    private boolean isPalindrome(String segment) {
        if (segment == null || segment.length() == 0) return false;
        int i = 0, j = segment.length()-1;
        while (i <= j) {
            if (segment.charAt(i) != segment.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    private void backtracking2(String s, int start, List<String> cur, List<List<String>> ans) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                cur.add(s.substring(start, i+1));
                backtracking2(s, i+1, cur, ans);
                cur.remove(cur.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end)
            if(s.charAt(start++) != s.charAt(end--)) return false;
        return true;
    }
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        backtracking2(s, 0, new ArrayList<>(), ans);
        return ans;
    }
    public static void main(String[] args) {
        P131PalindromePartitioning p131 = new P131PalindromePartitioning();
        // 测试：
        //"aab" ====> [["a","a","b"],["aa","b"]]
        //"a" ====> [["a"]]
        //"" ====> [[]]
        //"bb" ====> [["b","b"],["bb"]]
        System.out.println(p131.partition(""));
    }
}
