package dp.seq.stock;


/**
* Title: 122. 买卖股票的最佳时机 III
* Desc: 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
* Created by Myth-MBP on 04/07/2020 in VSCode
*/
public class P123BuyAndSellStock3 {
    // 状态定义：dp[i][j] 表示在 [0, i] 区间里（这个状态依然是前缀性质的），状态为 j 的最大收益。j 的含义如下：
    // j = 0：还未开始交易；
    // j = 1：第 1 次买入一支股票；
    // j = 2：第 1 次卖出一支股票；
    // j = 3：第 2 次买入一支股票；
    // j = 4：第 2 次卖出一支股票
    
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // dp[i][j] ，表示 [0, i] 区间里，状态为 j 的最大收益
        // j = 0：什么都不操作
        // j = 1：第 1 次买入一支股票
        // j = 2：第 1 次卖出一支股票
        // j = 3：第 2 次买入一支股票
        // j = 4：第 2 次卖出一支股票

        // 初始化
        // 第 0 天的时候很容易初始化前两个状态，而状态 3 （表示第 2 次持股）只能赋值为一个不可能的数。
        // 注意：只有在之前的状态有被赋值的时候，才可能有当前状态。
        int[][] dp = new int[len][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 3 状态都还没有发生，因此应该赋值为一个不可能的数
        for (int i = 0; i < len; i++) {
            dp[i][3] = Integer.MIN_VALUE;
        }

        // 状态转移只有 2 种情况：
        // 情况 1：什么都不做
        // 情况 2：由上一个状态转移过来
        for (int i = 1; i < len; i++) {
            // j = 0 的值永远是 0
            dp[i][0] = 0;
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        // 最大值只发生在不持股的时候，因此来源有 3 个：j = 0 ,j = 2, j = 4
        // 最后一天不持股的状态都可能成为候选的最大利润。
        return Math.max(0, Math.max(dp[len - 1][2], dp[len - 1][4]));
    }

    public static void main(String[] args) {
        
    }
}