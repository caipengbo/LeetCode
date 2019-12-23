package dp.partition;

/**
* Title:  312. 戳气球(区间类DP Hard)
* Desc: 
* Created by Myth on 12/23/2019 in VSCode
*/
/*
例如：[3,1,5,8]
第一步，算长度为1的区间。
    先算3,1,5,8。
第2步，算长度为2的区间。
    再算3,1 ; 1,5 ; 5,8。
    对于3,1。3最后爆，1已经算出；同样1最后爆，3已经算出。
    对于1,5。1最后爆，5已经算出；同样5最后爆，1已经算出。
    对于5,8。5最后爆，8已经算出；同样8最后爆，5已经算出。
第3步，算长度为3的区间。
    再算3,1,5 ; 1,5,8。
    对于3,1,5。3最后爆，1,5已经算出；1最后爆，3和5已经算出。 5最后爆，3,1已经算出。
    对于1,5,8。1最后爆，5,8已经算出；5最后爆，1和8已经算出。 8最后爆，1,5已经算出。
第4步，算3，1，5，8.
    3最后爆，1，5，8已经算出。1最后爆,3和5，8已经算出。5最后爆，3，1和8已经算出。8最后爆，3，1，5已经算出。
完整的思路：
    A序列;
    [i,j]区间;
    d[i][j]:储存结果;
    c：区间长度;
    for(c in [1,N]){
        for(i in [1,N-c+1]){
            j = i+c-1;
            for(k in [i,j]){
                d[i][j] = max(dp[i][j], A[i-1]*A[k]*A[j+1] + d[i][k-1]+d[k+1][j]);
            }
        }
    }
*/
public class P312BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] nums2 = new int[n+2];
        for (int i = 0; i < n; i++) {
            nums2[i+1] = nums[i];
        }
        nums2[0] = 1;
        nums2[n+1] = 1; 
        int[][] dp = new int[n+2][n+2];
        
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i+l-1 <= n; i++) {
                int j = i + l - 1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], nums2[i-1] * nums2[k] * nums2[j+1] + dp[i][k-1] + dp[k+1][j]);
                }
            }
        }
        return dp[1][n];
    }
    public static void main(String[] args) {
        P312BurstBalloons p312 = new P312BurstBalloons();
        int[] nums = {3, 1, 5, 8};
        System.out.println(p312.maxCoins(nums));
    }
}