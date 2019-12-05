package search.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 47. 全排列2
 * Desc: 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例： [1,1,2]  ->  [1,1,2], [1,2,1], [2,1,1]
 * Created by Myth on 7/18/2019
 */
public class P47Permutations2 {
    private void backtracking(int[] nums, int count, boolean[] visited, List<Integer> cur, List<List<Integer>> ans) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                // 去重, 注意 !visited[i-1] 这个判断条件
                // 如果不加这个条件的话，就永远不能选重复的元素，把所有的枝都减了！！！
                if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
                // if (i > 0 && nums[i] == nums[i-1]) continue;
                visited[i] = true;
                cur.add(nums[i]);
                backtracking(nums, count+1, visited, cur, ans);
                visited[i] = false;
                cur.remove(cur.size()-1);
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        boolean[] visited = new boolean[nums.length];
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, 0, visited, cur, ans);
        return ans;
    }
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2};
        P47Permutations2 p47 = new P47Permutations2();
        System.out.println(p47.permuteUnique(nums1));
    }
}
