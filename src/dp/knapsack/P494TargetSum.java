package dp.knapsack;

class P494TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0, target;
        for (int num : nums) {
            sum += num;
        }
        // if (sum == S) return 1;  
        if (sum < S || (sum + S) %  2 != 0) return 0;
        // 转化成 01 背包问题
        target = (sum + S) / 2;
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {      
                dp[i] += dp[i-num];
            }
        }
        return dp[target];
    }
}
