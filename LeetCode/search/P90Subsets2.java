package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 90. 子集 2
 * Desc: 在 78 题的基础上，nums是可能包含重复元素的整数数组
 * Created by Myth on 7/17/2019
 */
public class P90Subsets2 {
    private void backtracking(int[] nums, int start, List<Integer> cur, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(cur));
        for (int i = start; i < nums.length; i++) {
            // 和上一个数字相等就跳过去, 注意 i > start
            if (i > start && nums[i] == nums[i-1]) continue;
            cur.add(nums[i]);
            backtracking(nums, i+1, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, 0, cur, ans);
        return ans;
    }
    public static void main(String[] args) {
        P90Subsets2 p90 = new P90Subsets2();
        int[] arr1 = {1, 2, 2};
        System.out.println(p90.subsetsWithDup(arr1)); // [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
    }

}
