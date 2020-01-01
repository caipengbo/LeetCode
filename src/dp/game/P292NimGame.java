package dp.game;

/**
* Title: 292. Nim游戏（Bash博弈）
* Desc: 本题是 取1-3颗石子
* Created by Myth on 01/01/2020 in VSCode
*/

public class P292NimGame {
    
    public boolean canWinNim(int n) {
        if (n <= 3) return true;
        boolean[] dp = new boolean[n];
        // 最后的几种状态
        dp[n-1] = true;
        dp[n-2] = true;
        dp[n-3] = true;

        for (int i = n-4; i >= 0; i--) {  // 从后往前
            dp[i] = !dp[i+1] || !dp[i+2] || !dp[i+3];  // 此处的取反和Lintcode395中的 -dp道理一样，对手和自己的角色互换了
        }

        return dp[0];
    }
    // 状态压缩  Test n = 
    public boolean canWinNim2(int n) {
        if (n <= 3) return true;
        boolean[] dp = new boolean[4];
        dp[1] = true;
        dp[2] = true;
        dp[3] = true;

        for (int i = n-4; i >= 0; i--) {
            dp[0] = !dp[1] || !dp[2] || !dp[3];  // 推广到 每次最多取 n 次
            dp[3] = dp[2];
            dp[2] = dp[1];
            dp[1] = dp[0]; 
        }

        return dp[0];
    }


}