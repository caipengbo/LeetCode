package search.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 40. Combination Sum 2
 * Desc: 在39的基础上，允许candidates有重复元素,
 * 说明：
 *      ！！！candidates 中的每个数字在每个组合中 只能 使用一次(39题是可以重复选择任意次)
 *      所有数字（包括目标数）都是正整数。
 *      解集不能包含重复的组合。
 * Created by Myth on 7/16/2019
 */
public class P40CombinationSum2 {
    // 回溯（需要排序candidates）
    private void backtracking(int[] candidates, int target, int i, int sum, List<Integer> cur, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            // 去除重复组合
            if (j > i && candidates[j] == candidates[j-1]) continue;
            if (sum+candidates[j] > target) break; // 剪枝（要求candidates增序）
            cur.add(candidates[j]);
            // 每个数字只能选一次 所以j+1
            backtracking(candidates, target, j+1, sum+candidates[j], cur, ans);
            cur.remove(cur.size()-1);
        }

    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (target <= 0) return ans;
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void main(String[] args) {
        P40CombinationSum2 p40 = new P40CombinationSum2();
        int[] can = {10,1,2,7,6,1,5};
        int tar = 8;
        System.out.println(p40.combinationSum2(can, tar)); // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]

    }

}
