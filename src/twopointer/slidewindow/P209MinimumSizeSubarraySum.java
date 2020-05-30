package twopointer.slidewindow;

/**
* Title: 209. 长度最小的子数组（双指针滑动窗口）
* Desc: 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
* Created by Myth on 01/13/2020 in VSCode
*/

public class P209MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length, res = n + 1;
        int i = 0, j;
        for (j = 0; j < n; j++) {
            s -= nums[j];
            while (s <= 0) {
                res = Math.min(res, j-i+1);
                s += nums[i++];
            }
        }
        return res % (n + 1); // res == n+1 说明不存在，返回0
    }
}