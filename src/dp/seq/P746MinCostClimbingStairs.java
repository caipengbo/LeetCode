package dp.seq;

/**
 * Title: 746. 使用最小花费爬楼梯（70题的进阶版）
 * Desc: 数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * Created by Myth-Lab on 11/14/2019
 */
public class P746MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        dp[n] = Math.min(dp[n-1], dp[n-2]);
        return dp[n];
    }
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int dp0 = cost[0], dp1 = cost[1], temp;

        for (int i = 2; i < n; i++) {
            temp = dp0;
            dp0 = dp1;
            dp1 = Math.min(temp, dp0) + cost[i];
        }
        return Math.min(dp0, dp1);
    }

    public static void main(String[] args) {
        P746MinCostClimbingStairs p746 = new P746MinCostClimbingStairs();
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int[] cost2 = {10, 15, 20};
        System.out.println(p746.minCostClimbingStairs2(cost2));
    }
}
