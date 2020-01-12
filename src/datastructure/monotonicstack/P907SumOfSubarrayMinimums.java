package datastructure.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Title: 907.(Hard) 子数组的最小值之和 
 * Desc: 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A
 * 的每个（连续）子数组。由于答案可能很大，因此返回答案模 10^9 + 7。 
 * Created by Myth on 01/11/2020 in VSCode
 */

public class P907SumOfSubarrayMinimums {
    // left[] 左边比当前数cur大的 长度 + 1
    // right[] 右边比cur大的 长度 + 1
    // 以cur为最小值的子数组数目 = left[i] * right[i]
    public int sumSubarrayMins(int[] A) {
        int n = A.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            right[i] = n-i;
            while (!stack.empty() && A[stack.peek()] > A[i]) {
                int peek = stack.pop();
                right[peek] = i - peek; // 有此赋值，说明能找到next less element
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = 0; i < n; i++) {
            // left[i] = i + 1;
            while (!stack.empty() && A[stack.peek()] > A[i]) {
                stack.pop();
            }
            left[i] = (stack.empty() ? i+1 : i - stack.peek());
            stack.push(i);
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        int sum = 0, mod = (int)1e9 + 7;
        for (int i = 0; i < n; i++) {
            // sum += (A[i] * left[i] * right[i]) % mod;   // Error
            sum = (sum + A[i] * left[i] * right[i]) % mod;
        }
        return sum;
    }
    public int sumSubarrayMins2(int[] A) {
        int n = A.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            right[i] = n-i;  // 为什么初始值是n-i
            while (!stack.empty() && A[stack.peek()] > A[i]) {
                int peek = stack.pop();
                right[peek] = i - peek; // 不能再内部判断i-peek是否为0
            }
            left[i] = (stack.empty() ? i+1 : i - stack.peek());
            stack.push(i);
        }
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        int sum = 0, mod = (int)1e9 + 7;
        for (int i = 0; i < n; i++) {
            sum = (sum + A[i] * left[i] * right[i]) % mod;
        }
        return sum;
    }
    public static void main(String[] args) {
        P907SumOfSubarrayMinimums p907 = new P907SumOfSubarrayMinimums();
        int[] A = {3, 1, 2, 4};
        System.out.println(p907.sumSubarrayMins2(A));
    }
}