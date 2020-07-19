package twopointer.seq;

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
    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int ret = 0;
        while (left < right) {
            // 左侧
            // 小的一侧进行计算
            if (height[left] <= height[right]) {
                if (height[left] > leftMax) leftMax = height[left];
                else ret += leftMax - height[left];
                left++;
            } else {  // 右侧
                if (height[right] > rightMax) rightMax = height[right];
                else ret += rightMax - height[right];
                right--;
            }
        }
        return ret;
    }
}
