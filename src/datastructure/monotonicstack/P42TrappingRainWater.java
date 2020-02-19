package datastructure.monotonicstack;

import java.util.Stack;

/**
* Title: 42. 接雨水（单调栈解法，双指针解法在双指针包内）
* Desc: 
* Created by Myth on 01/08/2020 in VSCode
*/

public class P42TrappingRainWater {
    // 递减栈，分层求雨水（难点）
    public int trap2(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        Stack<Integer> stack = new Stack<>();  // 为什么要存下标？？ ->  计算宽
        int sum = 0, i = 0;

        while (i < n) {
            if (stack.empty() || height[stack.peek()] >= height[i]) {
                stack.push(i++);
            } else {
                int d = stack.pop();
                if (!stack.empty()) {  // 防止出现左边无边界的情况
                    // 开始可以存水 
                    int h = height[i] < height[stack.peek()] ? i : stack.peek();  // 短的边作为高 
                    sum += (height[h]-height[d]) * (i-stack.peek()-1);
                }
            }
        }
        return sum;
    }

    // 第二遍：递减栈，碰到递增的元素（就出现了碗一样的结构），便可以统计这个碗盛水
    // 注意这个碗的两个边并不是一样长的，取较小的一边为碗高
    public int trap(int[] height) {
        int n = height.length, i = 0;
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        while (i < n) {
            while (!stack.empty() && height[stack.peek()] < height[i]) {
                int d = stack.pop();
                if (!stack.empty()) {
                    int h = height[i] < height[stack.peek()] ? i : stack.peek();
                    // sum += (height[h]-height[d]) * (i-h-1);  // 注意宽是什么？
                    sum += (height[h]-height[d]) * (i-stack.peek()-1);
                }
            }
            stack.push(i++);
        }
        return sum;
    }

    public static void main(String[] args) {
        P42TrappingRainWater p42 = new P42TrappingRainWater();
        int[] height0 = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height1 = {0,1,0,2};
        int[] height2 = {2,1,0,1,3};
        int[] height3 = {0,1,2};
        int[] height4 = {0,1,2,1,0};
        int[] height5 = {0,1};
        int[] height6 = {0};
        int[] height7 = {4,2,3};
        System.out.println(p42.trap(height0));  // 6
        System.out.println(p42.trap(height1));  // 1
        System.out.println(p42.trap(height2));  // 4
        System.out.println(p42.trap(height3));  // 0
        System.out.println(p42.trap(height4));  // 0
        System.out.println(p42.trap(height5));  // 0
        System.out.println(p42.trap(height6));  // 0
        System.out.println(p42.trap(height7));  // 1
    }
}