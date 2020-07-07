package datastructure.monotonicstack;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;;

/**
* Title: 496. 下一个更大元素 I
* Desc: 
* Created by Myth on 01/09/2020 in VSCode
*/

public class P496NextGreaterElement1 {
    // ====================最终版本
    // 理清楚之后的版本(pop求后)
    public int[] nextGreaterElement3(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums2.length;
        int[] ret = new int[nums1.length];
        for (int i = 0; i < n; i++) {
            while (!stack.empty() && stack.peek() < nums2[i]) {  // 递减栈
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        for (int j = 0; j < nums1.length; j++) {
            ret[j] = map.getOrDefault(nums1[j], -1);
        }
        return ret;
    }
    // ===========================练习版本==========
    // 注意两种方法统计方法存的东西
    // push的时候统计(从后往前)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int i = nums2.length-1;
        int[] ret = new int[nums1.length];
        while (i >= 0) {
            if (stack.empty()) {
                map.put(nums2[i], -1);
                stack.push(i--);
            } else if (nums2[stack.peek()] > nums2[i]) {
                map.put(nums2[i], nums2[stack.peek()]);
                stack.push(i--);
            } else {
                stack.pop();
            }
        }
        for (int j = 0; j < nums1.length; j++) {
            ret[j] = map.get(nums1[j]);
        }
        return ret;
    }
    // 推荐此种写法，比较直观
    // pop的时候统计（√）
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, n = nums2.length;
        int[] ret = new int[nums1.length];
        while (i < n) {
            if (stack.empty() || stack.peek() > nums2[i]) {
                stack.push(nums2[i++]);
            } else {
                map.put(stack.pop(), nums2[i]);
            }
        }
        for (int j = 0; j < nums1.length; j++) {
            ret[j] = map.getOrDefault(nums1[j], -1);
        }
        return ret;
    }
    
    
    public static void main(String[] args) {
        P496NextGreaterElement1 p496 = new P496NextGreaterElement1();
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] nums11 = {2, 4};
        int[] nums22 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(p496.nextGreaterElement(nums1, nums2)));
        System.out.println(Arrays.toString(p496.nextGreaterElement(nums11, nums22)));
    }
}