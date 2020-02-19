package divideconquer;

import java.util.Arrays;

/**
* Title: 【经典分治例题】统计逆序对
* Desc: 求逆序对（顺序是从小到大）
    https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
* Created by Myth on 01/15/2020 in VSCode
*/

public class InversionCountArray {
    public int mergeSort(int[] nums, int start, int end) {
        if (start >= end) return 0;
        int mid = (start + end) / 2;
        int count = 0;
        count += mergeSort(nums, start, mid);
        count += mergeSort(nums, mid+1, end);
        count += merge(nums, start, mid, mid+1, end);
        return count;
    }
    public int merge(int[] nums, int start1, int end1, int start2, int end2) {
        if (start1 > end1 || start2 > end2) return 0;
        int[] temp = Arrays.copyOf(nums, nums.length);
        int i = start1, count = 0;
        while (start1 <= end1 && start2 <= end2) {
            if (temp[start1] <= temp[start2]) nums[i++] = temp[start1++];
            else {
                // temp[start1] > temp[start2]  start后面的数都大于 temp[start2]
                count += (end1 - start1 + 1);
                nums[i++] = temp[start2++];
            }
        }
        while (start1 <= end1) nums[i++] = temp[start1++];
        while (start2 <= end2) nums[i++] = temp[start2++];
        return count;
    }
    public static void main(String[] args) {
        InversionCountArray inver = new InversionCountArray();
        int[] arr0 = {20, 1};
        int[] arr1 = {1, 20, 6, 4, 5};
        int[] arr2 = {2, 4, 1, 3, 5};
        System.out.println(inver.mergeSort(arr0, 0, 1));  // 1
        System.out.println(inver.mergeSort(arr1, 0, 4));  // 5
        System.out.println(inver.mergeSort(arr2, 0, 4));  // 3
    }
}