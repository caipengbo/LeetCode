package search.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 46. 全排列
 * Desc: 给定一个 没有重复数字 的序列，返回其所有可能的全排列。
 * Created by Myth on 7/18/2019
 */
public class P46Permutations {
    private void backtracking(int[] nums, int count, boolean[] visited, List<Integer> cur, List<List<Integer>> ans) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(cur));

            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // if (cur.contains(nums[i])) continue; 使用一个boolean数组来记录是否的包含该元素
            if (!visited[i]) {
                visited[i] = true;
                cur.add(nums[i]);
                backtracking(nums, count+1, visited, cur, ans);
                visited[i] = false;
                cur.remove(cur.size()-1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        boolean[] visited = new boolean[nums.length];
        List<Integer> cur = new ArrayList<>();
        backtracking(nums, 0, visited, cur, ans);
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        P46Permutations p46 = new P46Permutations();
        System.out.println(p46.permute(nums1));
    }
}
