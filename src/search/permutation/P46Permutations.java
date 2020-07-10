package search.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 46. 全排列
 * Desc: 给定一个 没有重复数字 的序列，返回其所有可能的全排列。
 * Created by Myth on 7/18/2019
 */
public class P46Permutations {

    // ========= 固定一个，然后与后面的交换
    // 固定第一个字符，递归取得首位后面的各种字符串组合；
    // 再把第一个字符与后面每一个字符交换，并同样递归获得首位后面的字符串组合；
    // 递归的出口，就是只剩一个字符的时候，递归的循环过程，就是从每个子串的第二个字符开始依次与第一个字符交换，然后继续处理子串。
    private void backtracking(int[] nums, List<List<Integer>> ans, int start, int end) {
        if (start == end) {
            List<Integer> cur = new ArrayList<>(nums.length);
            for (int num : nums) {
                cur.add(num);
            }
            ans.add(cur);
            return;
        }
        for (int i = start; i < end; i++) {
            swap(nums, start, i);
            backtracking(nums, ans, start+1, end);
            swap(nums, start, i);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        backtracking(nums, ans, 0, nums.length);
        return ans;
    }

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
        System.out.println(p46.permute2(nums1));
    }
}
