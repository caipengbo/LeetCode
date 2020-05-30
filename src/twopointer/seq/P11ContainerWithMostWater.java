package twopointer.seq;

/**
 * Title: 11. 盛最多水的容器
 * Desc: 说明：你不能倾斜容器，且 n (数组的长度)的值至少为 2。
 * Created by Myth on 10/7/2019
 */
public class P11ContainerWithMostWater {
    // 我们要做的就是保证宽度最大的情况下，高度最大
    // 一开始宽度最大，然后逐步减少宽度
    // 这个时候要不断的去更新高度，使得高度尽量的大，如何移动较大的一端，那么面积肯定是减小的
    // 移动较小的那一个端，面积有可能增大
    public int maxArea(int[] height) {
        int i = 0, j = height.length-1;
        int maxValue = 0;
        while (j - i >= 1) {
            System.out.println((j-i) * Math.min(height[i], height[j]));
            maxValue = Math.max(maxValue, (j-i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) {
                i++;
            }
            else if (height[i] > height[j]) {
                j--;
            } else {  // 注意此处没必要去区分相等的情况。见下面的代码 maxArea2
                if (height[i+1] < height[j-1]) i++;
                else j--;
            }
        }
        return maxValue;
    }
    public int maxArea2(int[] height) {
        int i = 0, j = height.length-1;
        int maxValue = 0;
        while (j - i >= 1) {
            System.out.println((j-i) * Math.min(height[i], height[j]));
            maxValue = Math.max(maxValue, (j-i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxValue;
    }
    public static void main(String[] args) {
        P11ContainerWithMostWater p11 = new P11ContainerWithMostWater();
        int[] arr = {1,8,6,2,5,4,8,3,7};
        int[] arr2 = {6,2,5,4};
        p11.maxArea(arr2);
        System.out.println("======");
        p11.maxArea2(arr2);
    }
}
