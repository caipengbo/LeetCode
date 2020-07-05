package datastructure;

import java.util.HashMap;
import java.util.Map;

/**
* Title: 560. 和为K的子数组 
* Desc: 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
* Created by Myth-MBP on 05/07/2020 in VSCode
*/
public class P560SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;  // 注意此处千万不能break,因为有重复的情况 比如 数组 0 0 0 0 0 0 
            }
        }
        return count;
    }
    // 使用区间和
    public int subarraySum1_1(int[] nums, int k) {
        int len = nums.length;
        // 计算前缀和数组
        int[] preSum = new int[len + 1];  // 前缀和，不包括当前元素
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                // 区间和 [left..right]，注意下标偏移
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }
    // 对上面进行优化，
    // 前缀和 + Hash
    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(n);
        map.put(0, 1);  // 注意此处，实例：[1,1,1] 2
        int pre = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            // 如果此句放在这里，当[1]  0 时候会出错
            // map.put(pre, map.getOrDefault(pre, 0)+1);
            if (map.containsKey(pre-k)) {
                count += map.get(pre-k);
            }
            map.put(pre, map.getOrDefault(pre, 0)+1);
        }
        return count;
    }
    // preSum[i] - preSum[j-1] = k   -> preSum[i] - k = preSum[j-1]
    public int subarraySum3(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n];
        Map<Integer, Integer> map = new HashMap<>(n);
        int pre = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            sums[i] = pre;
            map.put(pre, map.getOrDefault(pre, 0)+1);
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(sums[i]+k)) {
                count += map.get(sums[i]+k);
            }
        }
        return count;
    }
}