package dp.twoseq;

/**
 * Title: 718. 最长重复子数组（最长公共子序列1143题）
 * Desc:  给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。（注意区分子串与子序列）
 * Created by Myth-Lab on 12/3/2019
 */
public class P718MaximumLengthOfRepeatedSubarray {

    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n+1][m+1];
        int result = 0;  // 勿忘此处
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    result = Math.max(result, dp[i][j]);
                }

            }
        }
        return dp[n][m];
    }
}
