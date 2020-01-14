package datastructure.monotonicqueue;

import java.util.Arrays;

/**
 * Title: 跳台阶 Desc: A Frog jumps K steps at most. It costs `A[i]` to stays at
 * `i`. Return the min cost to get to `N-1` from `0` Created by Myth on
 * 01/14/2020 in VSCode
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
    /** 使用单调栈优化
from collections import deque
def min_cost(A, K):
    Q = deque([(0, A[0])])
    for i in range(1, len(A)):
        
        # keep sliding width == K steps
        while Q and Q[0][0] < i - K:
            Q.popleft()
            
        # remove inferior elements at the tail
        while Q and Q[-1][1] > A[i] + Q[0][1]:
            Q.pop()

        Q.append((i, A[i] + Q[0][1]))
    return Q[-1][1]
     * 
     */
    public static void main(String[] args) {
        int[] costs = {0, 3, 2, 7, 1, 4};
        FrogJump2 frogJump2 = new FrogJump2();
        frogJump2.jump(costs, 2);
    }

}