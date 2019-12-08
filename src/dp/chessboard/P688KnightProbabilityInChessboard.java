package dp.chessboard;

/**
* Title: 688. “马”在棋盘上的概率
* Desc: 
* Created by Myth on 12/07/2019 in VSCode
*/
// 在棋盘上的次数 / 总的次数（8^K）, 直接求8 的K次方容易溢出，所以在每个循环都除以8（*0.125）
public class P688KnightProbabilityInChessboard {
    // 统计某
    public double knightProbability(int N, int K, int r, int c) {
        // if (K == 0) return 1.0;
        int[][] dir = {{-1, -2}, {-2,-1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
        double[][][] dp = new double[N][N][K+1];
        dp[r][c][0] = 1;
        
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int d = 0; d < 8; d++) {
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];
                        if (x < 0 || x >= N || y < 0 || y >= N) continue;
                        dp[i][j][k] += dp[x][y][k-1] * 0.125;    
                    }
                }
            }
        }
        double sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dp[i][j][K] + " ");
                sum += dp[i][j][K];
            }
            System.out.println();
        }

        return sum;
    }
    public static void main(String[] args) {
        P688KnightProbabilityInChessboard p688 = new P688KnightProbabilityInChessboard();
        // System.out.println(p688.knightProbability(3, 0, 0, 0));
        System.out.println(p688.knightProbability(8, 30, 6, 4));
    }
}