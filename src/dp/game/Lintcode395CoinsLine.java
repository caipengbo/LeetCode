package dp.game;

/**
* Title: Lintcode395题：硬币排成线
* Desc: https://www.lintcode.com/problem/coins-in-a-line-ii/description
* Created by Myth on 12/31/2019 in VSCode
*/

public class Lintcode395CoinsLine {
    public boolean firstWillWin(int[] values) {
        int n = values.length;
        if (n == 0) return false;
        if (n <= 2) return true;
        int[] dp = new int[n];
        dp[n-1] = values[n-1];
        dp[n-2] = values[n-2] + values[n-1];
        dp[n-3] = values[n-3] + values[n-2] - values[n-1];
        for (int i = n-4; i >= 0; i--) {
            dp[i] = Math.max(values[i]-dp[i+1], values[i]+values[i+1]-dp[i+2])
        }
        return dp[0] > 0;
    }
}