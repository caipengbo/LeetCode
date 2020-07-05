package dp.seq.sub_seq;

/**
 * Title: P152. 乘积最大子序列
 * Desc: 给定一个数组（有正有负），找到乘积最大的连续子序列
 * Created by Myth-Lab on 11/26/2019
 */

public class P152MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        // dp[i][j] = dp[i][j-1]*nums[j]
        int n = nums.length;
        if (n == 0) return 0;
        int[][] dp = new int[n][n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
            max = Math.max(max, dp[i][i]);
        }
        
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] * nums[i]; 
            max = Math.max(max, dp[0][i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                dp[i][j] = dp[i][j-1] * nums[j];
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
    // 状态压缩
    public int maxProduct2(int[] nums) {
        // dp[i][j] = dp[i][j-1]*nums[j]
        int n = nums.length;
        if (n == 0) return 0;
        int cur = 1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) cur = nums[i]; 
                else cur = cur * nums[j];
                max = Math.max(max, cur);
            }
        }
        return max;
    }
    public static void main(String[] args) {
        P152MaximumProductSubarray p152 = new P152MaximumProductSubarray();
        int[] nums = {-2, 0};
        System.out.println(p152.maxProduct(nums));
    }
}