package datastructure.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Title: 503. 下一个更大元素 II 
 * Desc: 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * Created by Myth on 01/09/2020 in VSCode
 */

public class P503NextGreaterElement2 {
    // 2n
    // 循环数组
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Stack<Integer> stack = new Stack<>();  // 递减栈, 存放下标
        int i = 0, j;
        // 遍历两遍数组即可
        while (i < 2*n) {
            j = (i >= n ? i-n : i);
            if (stack.empty() || nums[stack.peek()] >= nums[j]) {
                stack.push(j);
                i++;
            } else {
                ret[stack.pop()] = nums[j];
            }
        }
        return ret;
    }
    // 简洁的写法
    public int[] nextGreaterElements2(int[] A) {
        int n = A.length, res[] = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && A[stack.peek()] < A[i % n])
                res[stack.pop()] = A[i % n];
            stack.push(i % n);
        }
        return res;
    }
}