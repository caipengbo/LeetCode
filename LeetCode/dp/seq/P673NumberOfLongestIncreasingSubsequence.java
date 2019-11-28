package dp.seq;

import java.util.Arrays;

/**
 * Title: 673. 最长递增子序列的个数
 * Desc: 在300题的基础上，进行修改：统计最长递增子序列的个数
 * Created by Myth-Lab on 11/26/2019
 */
public class P673NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];  // 以nums[i]结尾的序列长度
        int[] counts = new int[n];  // 对应该长度的数目
        Arrays.fill(counts, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 此处是难点和重点
                    if (dp[j] >= dp[i]) {
                        dp[i] = dp[j] + 1;
                        counts[i] = counts[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }
        int longest = 0, ans = 0;
        for (int length: dp) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < n; ++i) {
            if (dp[i] == longest) {
                ans += counts[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7};
        P673NumberOfLongestIncreasingSubsequence p673 = new P673NumberOfLongestIncreasingSubsequence();
        System.out.println(p673.findNumberOfLIS(nums));
    }
}
