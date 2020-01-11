package datastructure.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Title: 907.(Hard) 子数组的最小值之和 Desc: 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A
 * 的每个（连续）子数组。由于答案可能很大，因此返回答案模 10^9 + 7。 Created by Myth on 01/11/2020 in VSCode
 */

public class P907SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] A) {
        int n = A.length;
        int[][] B = new int[n][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            B[i][0] = 1;
            B[i][1] = 1;
            while (!stack.empty() && A[stack.peek()] > A[i]) {
                int peek = stack.pop();
                B[peek][1] = i - peek;  // 右边界
            }
            if (!stack.empty()) B[i][0] = i - stack.peek();  // 左边界
            stack.push(i);
        }
        int sum = 0, mod = 100000007;
        for (int i = 0; i < n; i++) {
            sum += (long)(A[i] * B[i][0] * B[i][1]) % mod;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(B[i][0] + ", " + B[i][1]);
        }
        return sum;
    }
    public static void main(String[] args) {
        P907SumOfSubarrayMinimums p907 = new P907SumOfSubarrayMinimums();
        int[] A = {3, 1, 2, 4};
        System.out.println(p907.sumSubarrayMins(A));
    }
}