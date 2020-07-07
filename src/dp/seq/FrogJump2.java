package dp.seq;

import java.util.Arrays;

/**
 * Title: 跳台阶 
 * Desc: A Frog jumps K steps at most. It costs `A[i]` to stays at `i`. 
 * Return the min cost to get to `N-1` from `0` 
 * Created by Myth on 01/14/2020 in VSCode
 */

public class FrogJump2 {
    
    public int jump(int[] costs, int K) {
        int n = costs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i <= K && i < n; i++) {
            dp[i] = costs[i];
        }

        for (int i = K+1; i < n; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i] = Math.min(dp[i], dp[i-j]+costs[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n-1];

    }
    public static void main(String[] args) {
        int[] costs = {0, 3, 2, 7, 1, 4};
        FrogJump2 frogJump2 = new FrogJump2();
        frogJump2.jump(costs, 2);
    }

}