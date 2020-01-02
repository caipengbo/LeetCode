package dp.game;

/**
* Title:  486. 预测赢家
* Desc: Lintcode395的进阶版，395是从一端取，本题是可以从两端取
* Created by Myth on 01/02/2020 in VSCode
*/

public class P486PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        if (n == 0) return true;
        int[][] dp = new int[n][n];  // dp[i][j] 代表子序列 nums[i...j] 先手与对手的差值
        // 初始值：j-i 分别为 0 1 2 时的值
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
            if (i+1 < n) {
                dp[i][i+1] = Math.abs(nums[i+1]-nums[i]);
            }
            if (i+2 < n) {
                dp[i][i+2] = Math.max(nums[i]-Math.abs(nums[i+1]-nums[i+2]), nums[i+2]-Math.abs(nums[i+1]-nums[i]));
            }
        }

        for (int i = n-3; i >= 0; i--) {  // 根据转移方程确定遍历的顺序和边界
            for (int j = i+3; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        }
        return dp[0][n-1] >= 0;
    }
    public static void main(String[] args) {
        P486PredictTheWinner p486 = new P486PredictTheWinner();
        int[] nums = {1, 5};
        System.out.println(p486.PredictTheWinner(nums));
    }
}