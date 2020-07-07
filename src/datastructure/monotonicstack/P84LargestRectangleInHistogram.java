package datastructure.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
* Title: 84. 柱状图中最大的矩形
* Desc: 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积。
* Created by Myth on 01/08/2020 in VSCode
*/

public class P84LargestRectangleInHistogram {
    // 为什么是单调栈问题？
    // 当前位置的下一个更小元素能组成矩形时，进行计算面积 
    // 第二遍 
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, max = 0;
        Stack<Integer> increaseStack = new Stack<>();
        // 递增栈，While peek > cur, loop pop, update max
        int cur, peek, w;
        for (int i = 0; i <= n; i++) {
            cur = (i == n ? 0 : heights[i]);  // Add 0 in last step
            while (!increaseStack.empty() && heights[increaseStack.peek()] > cur) {
                peek = increaseStack.pop();
                // w = (increaseStack.empty() ? i : i - peek);   // X  Test case : [0, 3, 2, 5]
                // 注意 此处：夹在中间的 才是 宽度 w 
                // 不能在push的时候处理，因为最后一块面积（w最大时）不一定面积最大，因为h不一定高，面积是由 w和h共同决定的
                w = increaseStack.isEmpty() ? i : (i-increaseStack.peek()-1);  // 举例【4 2】
                max = Math.max(max, w * heights[peek]);  // 一减小就计算
            }
            increaseStack.push(i);
        }

        return max;
    }
    // 第一遍 递增栈
    public int largestRectangleArea1(int[] heights) {
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
        int[] heights6 = {5, 4, 2, 3};
        int[] heights7 = {4,2,0,3,2,5};
        int[] heights8 = {0,3,2,5};
        System.out.println(p84.largestRectangleArea(heights0));  // 10
        System.out.println(p84.largestRectangleArea(heights1));  // 2
        System.out.println(p84.largestRectangleArea(heights2));  // 4
        System.out.println(p84.largestRectangleArea(heights3));  // 2
        System.out.println(p84.largestRectangleArea(heights4));  // 3
        System.out.println(p84.largestRectangleArea(heights5));  // 6
        System.out.println(p84.largestRectangleArea(heights6));  // 8
        System.out.println(p84.largestRectangleArea(heights7));  // 6
        System.out.println(p84.largestRectangleArea(heights8));  // 6
    }
}