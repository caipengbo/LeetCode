package search.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 216. Combination Sum III
 * Desc: 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 *      所有数字都是正整数。
 *      解集不能包含重复的组合。 
 * Created by Myth on 7/16/2019
 */
public class P216CombinationSum3 {
    // 回溯法
    private void backtracking(int k, int n, int start, List<Integer> cur, List<List<Integer>> ans) {
        if (k == 0 && n == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (n < i) break; // 剪枝
            cur.add(i);
            backtracking(k-1, n-i, i+1, cur, ans);
            cur.remove(cur.size()-1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k <=0 || n <= 0) return ans;
        List<Integer> cur = new ArrayList<>();
        backtracking(k, n, 1, cur, ans);
        return ans;
    }

    public static void main(String[] args) {
        P216CombinationSum3 p216 = new P216CombinationSum3();
        System.out.println(p216.combinationSum3(3, 7)); // [[1, 2, 4]]
        System.out.println(p216.combinationSum3(3, 9)); // [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
        System.out.println(p216.combinationSum3(3, 15)); // [[1, 5, 9], [1, 6, 8], [2, 4, 9], [2, 5, 8], [2, 6, 7], [3, 4, 8], [3, 5, 7], [4, 5, 6]]
    }
}
