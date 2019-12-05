package search.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 39. Combination Sum
 * Desc: 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 说明：
 *      candidates 中的数字可以无限制重复被选取
 *      所有数字（包括 target）都是正整数
 *      解集不能包含重复的组合
 * 和 17题的区别：17题没有约束条件（DFS列举出所有的情况），39题有约束条件（可以剪枝、回溯）
 * 相关题目：40、216
 * Created by Myth on 7/16/2019
 */
public class P39CombinationSum {
    // 回溯（需要排序candidates）
    // 参数说明：i 代表candidate的位置，cur代表当前形成的答案(最终答案之一)， ans代表最终答案
    private void backtracking(int[] candidates, int target, int i, int sum, List<Integer> cur, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if (sum+candidates[j] > target) break; // 剪枝（要求candidates增序）
            cur.add(candidates[j]);
            backtracking(candidates, target, j, sum+candidates[j], cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    // 累加变成累减 可以不用传递sum参数
    private void backtracking2(int[] candidates, int target, int i, List<Integer> cur, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if (candidates[j] > target) break; // 剪枝 因为排序了，所以后面的数字肯定都大于target
            cur.add(candidates[j]);
            backtracking2(candidates, target-candidates[j], j, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    // 不剪枝、不要求排序（适合短输入，在长输入的时候，效率会降低）
    private void backtracking3(int[] candidates, int target, int i, int sum, List<Integer> cur, List<List<Integer>> ans) {
        if (sum > target) return; // 不剪枝，加上判断条件
        if (sum == target) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            // if (sum+candidates[j] > target) break; // 剪枝（要求candidates增序）
            cur.add(candidates[j]);
            backtracking3(candidates, target, j, sum+candidates[j], cur, ans);
            cur.remove(cur.size()-1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (target <= 0) return ans;
        // Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0, new ArrayList<>(), ans);
        // backtracking2(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void main(String[] args) {
        P39CombinationSum p39 = new P39CombinationSum();
        int[] can1 = {2, 3, 6, 7};
        int tar1 = 7;
        System.out.println(p39.combinationSum(can1, tar1)); // [[2, 2, 3], [7]]
        int[] can2 = {8,7,4,3};
        int tar2 = 11;
        System.out.println(p39.combinationSum(can2, tar2)); // [[3,4,4],[3,8],[4,7]]
    }
}
