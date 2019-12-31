package dp.partition;

/**
* Title: 375. 猜数字大小 II (MiniMax)
* Desc: 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字,计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
* Created by Myth on 12/31/2019 in VSCode
*/

public class P375GuessNumberHigherOrLower2 {
    // 记忆化搜索 自上而下
    public int[][] mem;
    public int getMoneyAmount(int n) {
        mem = new int[n+1][n+1];
        return recursive(1, n); 
    }
    public int recursive(int low, int high) {
        if (low >= high) return 0;
        if (mem[low][high] != 0) return mem[low][high];
        int min = Integer.MAX_VALUE;
        for (int i = low; i < high; i++) {
            int temp = Math.max(recursive(low, i-1), recursive(i+1, high));
            min = Math.min(min, temp+i);
        }
        mem[low][high] = min;
        return min;
    }
    // DP 自下而上
    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n+2][n+2];
        // dp[i][j] = (max(dp[i][k-1], dp[k+1][j]) + k) : k-> [i, j) 
        // 边界控制是难点
        int temp;
        for (int i = n; i > 0; i--) {
            for (int j = i; j <= n; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k <= j; k++) {
                        temp = Math.max(dp[i][k-1], dp[k+1][j]);
                        dp[i][j] = Math.min(dp[i][j], temp+k);
                    }
                }
            }
        }
        return dp[1][n];
    }
    public static void main(String[] args) {
        P375GuessNumberHigherOrLower2 p375 = new P375GuessNumberHigherOrLower2();
        // System.out.println(p375.getMoneyAmount2(1));
        // System.out.println(p375.getMoneyAmount2(2));
        System.out.println(p375.getMoneyAmount2(3));
        // System.out.println(p375.getMoneyAmount2(10));
        // System.out.println(p375.getMoneyAmount2(30));
    }

}