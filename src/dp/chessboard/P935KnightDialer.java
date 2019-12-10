package dp.chessboard;

import java.util.Arrays;

/**
* Title: 935. 骑士拨号器
* Desc: 
* Created by Myth on 12/10/2019 in VSCode
*/

public class P935KnightDialer {
    public int knightDialer(int N) {
        if (N <= 0) return 0;
        if (N == 1) return 10;
        long[][] dp = new long[N][10];
        for (int j = 0; j < 10; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = (dp[i-1][4] + dp[i-1][6]) % 1000000007;
            dp[i][1] = (dp[i-1][6] + dp[i-1][8]) % 1000000007;
            dp[i][2] = (dp[i-1][7] + dp[i-1][9]) % 1000000007;
            dp[i][3] = (dp[i-1][4] + dp[i-1][8]) % 1000000007;
            dp[i][4] = (dp[i-1][3] + dp[i-1][9] + dp[i-1][0]) % 1000000007;
            dp[i][5] = 0;
            dp[i][6] = (dp[i-1][1] + dp[i-1][7] + dp[i-1][0]) % 1000000007;
            dp[i][7] = (dp[i-1][2] + dp[i-1][6]) % 1000000007;
            dp[i][8] = (dp[i-1][1] + dp[i-1][3]) % 1000000007;
            dp[i][9] = (dp[i-1][2] + dp[i-1][4]) % 1000000007;
        }
        long sum = 0;
        for (int j = 0; j < 10; j++) {
            sum = (sum + dp[N-1][j]) % 1000000007;
        }
        return (int)sum;
    }
    public static void main(String[] args) {
        P935KnightDialer p935 = new P935KnightDialer();
        System.out.println(p935.knightDialer(50));  // 267287516
    }

}