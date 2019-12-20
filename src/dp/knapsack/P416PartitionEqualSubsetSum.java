package dp.knapsack;

/**
* Title: 416. 分割等和子集（01背包问题）
* Desc: 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
* Created by Myth on 12/14/2019 in VSCode
*/

public class P416PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        else sum = sum / 2;
        boolean[][] dp = new boolean[n][sum+1];
        dp[0][0] = true;
        if (nums[0] <= sum) dp[0][nums[0]] = true;
        // System.out.println(Arrays.toString(dp[0]));
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = (dp[i-1][j]) || (nums[i] <= j && dp[i-1][j-nums[i]]);
            }
            // System.out.println(Arrays.toString(dp[i]));
        }
        return dp[n-1][sum];
    }
    // 优化（使用一维数组）
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        else sum = sum / 2;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i >= num; i--) {    // 要搞清楚数组里面存的是什么
                dp[i] = dp[i] || dp[i-num];
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[sum];
    }
    public static void main(String[] args) {
        P416PartitionEqualSubsetSum p416 = new P416PartitionEqualSubsetSum();
        int[] nums = {1, 5, 11, 5};
        p416.canPartition2(nums);
    }
}