package dp.knapsack;

/**
* Title: 813. 最大平均值和的分组
* Desc: 
* Created by Myth on 12/17/2019 in VSCode
*/

public class P813LargestSumOfAverages {
    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        // 开辟n+1的目的是让下标对应关系更清晰
        double[][] dp = new double[K+1][n+1];
        double[] sum = new double[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + (double)A[i-1];
            dp[1][i] = sum[i] / (double)(i);
        }
        for (int i = 2; i <= K; i++) {
            for (int j = 1; j <= n; j++) {
                for (int p = i-1; p < j; p++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][p] + ((sum[j]-sum[p])/(j-p)));  
                }
            }
        }
        return dp[K][n];
    }
    public static void main(String[] args) {
        P813LargestSumOfAverages p813 = new P813LargestSumOfAverages();
        int[] A = {9, 1, 2, 3, 9};
        System.out.println(p813.largestSumOfAverages(A, 3));
    }
}