package datastructure.monotonicqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Title: 862. 和至少为 K 的最短子数组 
 * Desc:
 * 209题（双指针）的进阶版，其中数字可以为负数，这样我们就不知道下一个数会使总和大还是小了，就不能用双指针的做法.
 * 
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。如果没有和至少为 K 的非空子数组，返回 -1 。 
 * Created by Myth on 01/14/2020 in VSCode
 */

public class P862ShortestSubarrayWithSumAtLeastK {
    // 单纯 前缀和（TLE）
    public int shortestSubarray_Old(int[] A, int K) {
        int n = A.length;
        int[] preSum = new int[n+1];  // 前缀和
        int ret = n+1;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + A[i-1];
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] >= K) ret = Math.min(ret, i-j);  
            }
        }
        return ret == n+1 ? -1 : ret;
    }
    // 优化
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] preSum = new int[n+1];
        int ret = n+1;
        Deque<Integer> deque = new ArrayDeque<>();  // 递增队列
        for (int i = 0; i < n; i++) {
            preSum[i+1] = preSum[i] + A[i];
        }
        for (int i = 0; i <= n; i++) {
            while (deque.size() > 0 && preSum[i] - preSum[deque.getFirst()] >=  K)
                ret = Math.min(ret, i - deque.pollFirst());
            // 使用递增队列 才能保证
            while (deque.size() > 0 && preSum[deque.getLast()] >= preSum[i])
                deque.pollLast();
            deque.addLast(i);
        }
        return ret == n+1 ? -1 : ret;
    }
}