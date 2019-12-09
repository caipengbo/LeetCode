package dp.chessboard;

/**
* Title: 576. 出界的路径数
* Desc: 
* Created by Myth on 12/09/2019 in VSCode
*/

public class P576OutOfBoundaryPaths {
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] dp = new int[m][n][N+1];
        int sum = 0;
        dp[i][j][0] = 1;
        for (int k = 0; k < N; k++) {
            for (int p = 0; p < m; p++) {
                for (int q = 0; q < n; q++) {
                    int temp = 0;
                    if(dp[p][q][k] != 0) {
                        // left and top
                        if (p + 1 >= m) temp++;
                        else dp[p+1][q][k+1] = (dp[p+1][q][k+1] + dp[p][q][k]) % 1000000007;
                        if (p - 1 < 0) temp++;
                        else dp[p-1][q][k+1] = (dp[p-1][q][k+1] + dp[p][q][k]) % 1000000007;
                        if (q + 1 >= n) temp++;
                        else dp[p][q+1][k+1] = (dp[p][q+1][k+1] + dp[p][q][k]) % 1000000007;
                        if (q - 1 < 0) temp++;
                        else dp[p][q-1][k+1] = (dp[p][q-1][k+1] + dp[p][q][k]) % 1000000007;
                    }
                    
                    sum = (int)(((long)sum + ((long)temp * (long)dp[p][q][k])) % 1000000007);
                }
                // System.out.println();
            }
            // for debug
            // for (int p = 0; p < m; p++) {
            //     for (int q = 0; q < n; q++) {
            //         System.out.print(dp[p][q][k+1] + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println("-----------");
        }
        return sum;
    }
    public static void main(String[] args) {
        P576OutOfBoundaryPaths p576 = new P576OutOfBoundaryPaths();
        // System.out.println(p576.findPaths(2, 2, 2, 0, 0));
        // System.out.println(p576.findPaths(1, 3, 3, 0, 1));
        System.out.println(p576.findPaths(8, 50, 23, 5, 26));  // AC: 914783380
        System.out.println(p576.findPaths(50, 50, 50, 0, 0));  // AC: 678188903
    }
}