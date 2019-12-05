package dp.seq.stock;

/**
 * Title: 309. 最佳买卖股票时机含冷冻期
 * Desc: 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * Created by Myth-Lab on 11/22/2019
 */
public class P309BestTimeToBuyAndSellStockWithCooldown {
    // TODO 股票6题： 多状态讲解
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-lab/
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    // 多状态转移方程
    // dp[i][0] (第i天没持股票时的利润) = Math.max(dp[i-1][0], dp[i-1][1]+prices[i])  卖出股票 利润++
    // dp[i][1] (第i天持有股票时的利润) = Math.max(dp[i-1][1], dp[i-2][0]-prices[i])  买入股票利润--
    // TODO：Study这个滚动数组
    public int maxProfit(int[] prices) {
        int noStock = 0, haveStock = Integer.MIN_VALUE;
        int noStockPre = 0, temp;
        for (int i = 0; i < prices.length; i++) {
            temp = noStock;
            noStock = Math.max(noStock, haveStock+prices[i]);
            haveStock = Math.max(haveStock, noStockPre-prices[i]);
            noStockPre = temp;

        }
        return noStock;
    }
}
