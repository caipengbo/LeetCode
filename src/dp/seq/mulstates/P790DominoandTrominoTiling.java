package dp.seq.mulstates;

/**
* Title: 790. 多米诺和托米诺平铺（经典题）
* Desc: 有两种形状的瓷砖：一种是 2x1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
* 给定 N 的值，有多少种方法可以平铺 2 x N 的面板？返回值 mod 10^9 + 7
*   XX  <- 多米诺
* 
*   XX  <- "L" 托米诺
*   X
* Created by Myth on 12/29/2019 in VSCode
*/

public class P790DominoandTrominoTiling {
    public int numTilings(int N) {
        long[][] dp = new long[N+1][4];
        // 4种状态
        dp[0][0] = 1;  // 两行都没铺
        dp[0][1] = 0;  // 上没铺，下铺
        dp[0][2] = 0;  // 下没铺，上铺
        dp[0][3] = 0;  // 都铺
        int mod = 1000000007;
        for (int i = 1; i <= N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][3]) % mod;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % mod;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % mod;
            // 取模是很耗时的
            dp[i][3] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % mod;  // 注意此处是
        }
        return (int)dp[N][0];  // 想一想为什么要返回dp[0]???  一定要保证N列的下一列没被铺
    }
    public static void main(String[] args) {
        P790DominoandTrominoTiling p790 = new P790DominoandTrominoTiling();
        System.out.println(p790.numTilings(1));  // 5
        System.out.println(p790.numTilings(2));  // 5
        System.out.println(p790.numTilings(3));  // 5
        System.out.println(p790.numTilings(4));  // 11
        System.out.println(p790.numTilings(5));  // 24
        System.out.println(p790.numTilings(6));  // 53
        System.out.println(p790.numTilings(7));  // 117
        System.out.println(p790.numTilings(30));  // 
        System.out.println(p790.numTilings(1000));  // 979232805
    }
}