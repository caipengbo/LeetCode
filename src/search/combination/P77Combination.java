package search.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 77. 组合
 * Desc: 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * Created by Myth on 7/17/2019
 */
public class P77Combination {
    private void backtracking(int n, int k, int start, List<Integer> cur, List<List<Integer>> ans) {
        if (k == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= n; i++) {
            cur.add(i);
            backtracking(n, k-1, i+1, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k <=0 || n <= 0) return ans;
        List<Integer> cur = new ArrayList<>();
        backtracking(n, k, 1, cur, ans);
        return ans;
    }
    public static void main(String[] args) {
        P77Combination p77 = new P77Combination();
        System.out.println(p77.combine(4, 2));
    }
}
