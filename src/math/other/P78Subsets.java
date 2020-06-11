package math.other;

import java.util.LinkedList;
import java.util.List;

/**
 * Title: 78. 子集(回溯算法章节有回溯解法)
 * Desc: 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。说明：解集不能包含重复的子集。
 * Created by Myth-Lab on 11/6/2019
 */
public class P78Subsets {
    // 当前是位运算解法
    // 0  —— 1 << nums.length  的二进制正好可以覆盖 数组的 选取
    public List<List<Integer>> subsets(int[] nums) {
        int n = 1 << nums.length;
        List<List<Integer>> ret = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> sub = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((1<<j)&(i)) != 0) sub.add(nums[j]);
            }
            ret.add(sub);
        }
        return ret;
    }

    public static void main(String[] args) {
        P78Subsets p78 = new P78Subsets();
        int[] nums = {1, 2, 3};
        System.out.println(p78.subsets(nums));
    }
}
