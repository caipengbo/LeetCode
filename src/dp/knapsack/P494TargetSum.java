package dp.knapsack;


// 01背包（要搞清数组中存的东西）
/*
 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
*/
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
            for (int i = target; i >= num; i--) {       // 从后往前
                dp[i] += dp[i-num];
            }
        }
        return dp[target];
    }
}
