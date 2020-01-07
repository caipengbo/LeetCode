package dp.game;

/**
* Title: 877. 和486题一模一样！！！
* Desc: 
* Created by Myth on 01/02/2020 in VSCode
*/

public class P877StoneGame {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        if (n == 0) return true;
        int[][] dp = new int[n][n];  // dp[i][j] 代表子序列 nums[i...j] 先手与对手的差值
        // 初始值：j-i 分别为 0 1 2 时的值
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
            if (i+1 < n) {
                dp[i][i+1] = Math.abs(piles[i+1]-piles[i]);
            }
            if (i+2 < n) {
                dp[i][i+2] = Math.max(piles[i]-Math.abs(piles[i+1]-piles[i+2]), piles[i+2]-Math.abs(piles[i+1]-piles[i]));
            }
        }

        for (int i = n-3; i >= 0; i--) {  // 根据转移方程确定遍历的顺序和边界
            for (int j = i+3; j < n; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1]);
            }
        }
        return dp[0][n-1] > 0;
    }

}