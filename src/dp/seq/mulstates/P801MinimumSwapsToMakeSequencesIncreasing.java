package dp.seq.mulstates;

/**
* Title: 801. 使序列递增的最小交换次数(两种状态)
* Desc: 
* Created by Myth on 12/30/2019 in VSCode
*/

public class P801MinimumSwapsToMakeSequencesIncreasing {
    // 第 i 次和第i-1次一共存在4种不同的情况，每一次有两种状态，所以就有 2X2=4次情况
    // 四种情况是(i-1 和 i)： 1. 不交换 不交换  2. 交换 交换  3. 不交换 交换   4. 交换 不交换
    // 因为输入总是有效的，所以肯定满足 A[i-1] < A[i] && B[i-1] < B[i] ||  A[i-1] < B[i] && B[i-1] < A[i]
    // 交换情况不同，递推公式也不同
    public int minSwap(int[] A, int[] B) {  // A.length = B.length
        int n = A.length;
        if (n == 0) return 0;
        int[][] dp = new int[n][2];  // dp[i][0] 不交换  dp[i][1] 交换
        for (int i = 0; i < n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = Integer.MAX_VALUE;
        }
        dp[0][0] = 0;
        dp[0][1] = 1;
        System.out.println(dp[0][0] + ", " + dp[0][1]);
        for (int i = 1; i < n; i++) {
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = dp[i-1][1] + 1;
            }
            if (A[i-1] < B[i] && B[i-1] < A[i]) {
                dp[i][0] = Math.min(dp[i][0], dp[i-1][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i-1][0]+1);
            }
            System.out.println(dp[i][0] + ", " + dp[i][1]);
        }
        return Math.min(dp[n-1][0], dp[n-1][1]);
    }
    public static void main(String[] args) {
        P801MinimumSwapsToMakeSequencesIncreasing p801 = new P801MinimumSwapsToMakeSequencesIncreasing();
        int[] A = {1,3,5,4};
        int[] B = {1,2,3,7};
        p801.minSwap(A, B);
    }
}