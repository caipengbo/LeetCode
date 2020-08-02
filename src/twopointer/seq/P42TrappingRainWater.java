package twopointer.seq;

import jdk.javadoc.internal.doclets.formats.html.resources.standard;

/**
 * Title: 42. 接雨水（双指针解法，更常用的是单调栈解法）
 * Desc: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * Created by Myth-Lab on 10/13/2019
 */
public class P42TrappingRainWater {

    // 暴力解法：当前列的水 等于 min(左边最高，右边最高) - 当前高度

    // 重点：当前位置左边最大值leftMax 右边最大值如何求？？？

    // 可以存在数组里，一次遍历从左往右遍历就可以求出来leftMax，然后从右往左一次遍历就可以求出来rightMax

    // 也可以使用双指针，
    public static int trap(int[] height) {
        int left = 0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int ret = 0;
        while (left < right) {
            // 左侧
            // 小的一侧进行计算
            if (height[left] < height[right]) { // 注意此处
                leftMax = Math.max(leftMax, height[left]);
                ret += leftMax - height[left++];
            } else {  // 右侧
                rightMax = Math.max(rightMax, height[right]);
                ret += rightMax - height[right--];
            }
        }
        return ret;
    }
    // public static int trap2(int[] A) {
    //     if (A == null || A.length == 0) {
    //         return 0;
    //     }
    //     int i = 0, j = A.length-1;
    //     int leftMax = A[i], rightMax = A[j];
    //     int sum = 0;
    //     while (i <= j) {
    //         if (leftMax < rightMax) {  // 注意此处
    //             leftMax = Math.max(leftMax, A[i]);
    //             if (leftMax > A[i]) {
    //                 System.out.println("left+" + (leftMax-A[i]));
    //             }
    //             sum += leftMax - A[i++];
    //         } else {
    //             rightMax = Math.max(rightMax, A[j]);
    //             if (rightMax > A[j]) {
    //                 System.out.println("right+" + (rightMax-A[j]));
    //             }
    //             sum += rightMax - A[j--];
    //         }
    //     }
    //     return sum;
    // }
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(P42TrappingRainWater.trap(height));
    }
}
