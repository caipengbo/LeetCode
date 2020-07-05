package datastructure.hash;

import java.util.HashMap;
import java.util.Map;
/**
* Title: 1. 两数之和
* Desc: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
* Created by Myth-MBP on 05/07/2020 in VSCode
*/
public class P1TwoSum {
    // 注意返回的是下标
    public int[] twoSum(int[] nums, int target) {
       int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            int key = target-nums[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(nums[i], i);
        }
        return null; 
    }
}