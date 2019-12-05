package twopointer;

/**
 * Title: 42. 接雨水
 * Desc: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * Created by Myth-Lab on 10/13/2019
 */
public class P42TrappingRainWater {
    // 水都是从高往低流，所以柱子下降就一定能存住水，为啥能存贮水呢？因为右边还有一个比它更高的柱子
    // 左右两端双指针，向中间最大的柱子聚拢，期间下降的地方就肯定能存住水
    // 重点：怎么聚拢
    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int ret = 0;
        while (left < right) {
            // 左侧
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
