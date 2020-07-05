package dp.seq.sub_seq;

import java.util.Arrays;

/**
 * Title: 300. 最长上升子序列（要是改成子串呢？子串一般就是双指针写法了）最长公共子序列（子串）问题见718、1143题
 * 进阶题：673统计最长子序列的个数
 * Desc: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * Created by Myth-Lab on 11/26/2019
 */
public class P300LongestIncreasingSubsequence {
    // 下面的写法用到了状态压缩,实际的做
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) temp = Math.max(temp, dp[j]);
            }
            dp[i] = temp + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    // 第二遍
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;  // 勿忘
        int[] dp = new int[len];
        // Arrays.fill(dp, 1);
        dp[0] = 1;
        int max = 1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        // return dp[len-1];
        return max;
    }
    
    // O(nlogn) 的解法比较难理解： 维护一个最长有序列表，代表最长有序列表
    // 1. 如果当前元素大于该列表的最后一个元素，就增加列表的长度
    // 2. 否则，替换列表内 最大的 比当前元素小的 那个元素

    public static void main(String[] args) {
        P300LongestIncreasingSubsequence p300 = new P300LongestIncreasingSubsequence();
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        System.out.println(p300.lengthOfLIS2(nums));
    }
}
