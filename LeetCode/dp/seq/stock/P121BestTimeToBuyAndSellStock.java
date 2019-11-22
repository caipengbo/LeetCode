package dp.seq.stock;

/**
 * Title: 121.买卖股票的最佳时机
 * Desc: (剑指offer 63题)
 * Created by Myth-Lab on 11/20/2019
 */
public class P121BestTimeToBuyAndSellStock {
    // 保存前面的profit, 保存minVal
    public int maxProfit(int[] prices) {
        int profit = 0, minVal = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] <= minVal) minVal = prices[i];
            else profit = Math.max(profit, prices[i]-minVal);
        }
        return profit;
    }
    // TODO 多状态类的转移方程
    public int maxProfit2(int[] prices) {
        return 0;
    }
}
