package dp.seq;

/**
 * Title: 300. 最长上升子序列
 * Desc: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * Created by Myth-Lab on 11/26/2019
 */
public class P300LongestIncreasingSubsequence {
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
}
