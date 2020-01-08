package datastructure.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
* Title: 84. 柱状图中最大的矩形
* Desc: 
* Created by Myth on 01/08/2020 in VSCode
*/

public class P84LargestRectangleInHistogram {
    // 递增栈
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int i = 0, h, peek, w;
        while (i <= n) {
            h = (i == n ? 0 : heights[i]);  // 设置最后一个元素为 0
            if (stack.empty() || heights[stack.peek()] <= h) {
                stack.push(i++);
            } else {
                peek = stack.pop();
                w = stack.isEmpty() ? i : (i-(stack.peek()+1));  // 举例【4 2】
                max = Math.max(max, heights[peek]*w);
            }
        }
        return max;
    }
    public static void main(String[] args) {
        P84LargestRectangleInHistogram p84 = new P84LargestRectangleInHistogram();
        int[] heights0 = {2,1,5,6,2,3};
        int[] heights1 = {2,1};
        int[] heights2 = {1,2,2};
        int[] heights3 = {2};
        int[] heights4 = {1, 2, 1};
        int[] heights5 = {4, 2, 3};
        System.out.println(p84.largestRectangleArea(heights0));  // 10
        System.out.println(p84.largestRectangleArea(heights1));  // 2
        System.out.println(p84.largestRectangleArea(heights2));  // 4
        System.out.println(p84.largestRectangleArea(heights3));  // 2
        System.out.println(p84.largestRectangleArea(heights4));  // 3
        System.out.println(p84.largestRectangleArea(heights5));  // 6
    }
}