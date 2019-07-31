package search.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 78. 子集
 * Desc: 给定一组 不含重复元素 的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * Created by Myth on 7/17/2019
 */
public class P78Subsets {
    private void backtracking(int[] nums, int start, List<Integer> cur, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(cur));
        for (int i = start; i < nums.length; i++) {
            cur.add(nums[i]);
            backtracking(nums, i+1, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        backtracking(nums, 0, cur, ans);
        return ans;
    }

    public static void main(String[] args) {
        P78Subsets p78 = new P78Subsets();
        int[] arr1 = {1, 2, 3};
        System.out.println(p78.subsets(arr1));
    }
}
