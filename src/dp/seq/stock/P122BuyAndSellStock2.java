package dp.seq.stock;


/**
* Title: 122. 买卖股票的最佳时机 II
* Desc: 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
* 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
* Created by Myth-MBP on 04/07/2020 in VSCode
*/
public class P122BuyAndSellStock2 {
    // 多状态DP
    // Case 1，今天我没有股票，有两种可能：
    //     昨天我手上就没有股票，今天不做任何操作（rest）； dp[i-1][0]
    //     昨天我手上有一只股票，今天按照时价卖掉了（sell），收获了一笔钱  dp[i-1][1] + prices[i]
    // Case 2，今天持有一只股票，有两种可能：
    //     昨天我手上就有这只股票，今天不做任何操作（rest）； dp[i-1][1]
    //     昨天我没有股票，今天按照时价买入一只（sell），花掉了一笔钱  dp[i-1][0] - prices[i]
    // 综上，第 i 天的状态转移方程为：
    // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
    // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // 0：持有现金
        // 1：持有股票
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 这两行调换顺序也是可以的
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }
    // 贪心做法
    // 今天比昨天上涨了只加上涨
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += (prices[i]-prices[i-1]);
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        
    }
}