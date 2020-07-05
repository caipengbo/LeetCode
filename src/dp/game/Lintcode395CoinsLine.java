package dp.game;

/**
* Title: Lintcode395题：硬币排成线
* Desc: https://www.lintcode.com/problem/coins-in-a-line-ii/description
有 n 个不同价值的硬币排成一条线。两个参赛者轮流从 左边 依次拿走 1 或 2 个硬币，直到没有硬币为止。计算两个人分别拿到的硬币总价值，价值高的人获胜。

请判定 先手玩家 必胜还是必败?

若必胜, 返回 true, 否则返回 false
* Created by Myth on 12/31/2019 in VSCode
*/

public class Lintcode395CoinsLine {
    public boolean firstWillWin(int[] values) {
        int n = values.length;
        // 最后的几种状态
        if (n == 0) return false;
        if (n <= 2) return true;
        int[] dp = new int[n];
        dp[n-1] = values[n-1];
        dp[n-2] = values[n-2] + values[n-1];
        dp[n-3] = values[n-3] + values[n-2] - values[n-1];
        for (int i = n-4; i >= 0; i--) {  // 从后往前
            dp[i] = Math.max(values[i]-dp[i+1], values[i]+values[i+1]-dp[i+2]);  // 注意此处的 -dp[i+1]和-dp[i+2]
        }
        return dp[0] > 0;
    }
}