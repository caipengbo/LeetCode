package divideconquer;

import java.util.Arrays;

/**
* Title: 【经典分治例题】统计逆序对（进阶版见315题）
* Desc: 求逆序对（顺序是从小到大）
    https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
* Created by Myth on 01/15/2020 in VSCode
*/

public class InversionCountArray {
    
    public int reversePairs(int[] nums) {
        return countReversePairs(nums, 0, nums.length-1);
    }

    public int countReversePairs(int[] nums, int start, int end) {
        if (start >= end) return 0;
        int mid = (start + end) / 2;
        int count = 0;
        count += countReversePairs(nums, start, mid);
        count += countReversePairs(nums, mid+1, end);
        // if (nums[mid] <= nums[mid+1]) {
        //     return count;
        // }
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
                // temp[start1] > temp[start2]  start1后面的数都大于 temp[start2]
                // 下一次再到这里的时候 temp[start2]就变了，不会
                count += (end1 - start1 + 1);
                nums[i++] = temp[start2++];
            }
        }
        while (start1 <= end1) nums[i++] = temp[start1++];
        while (start2 <= end2) nums[i++] = temp[start2++];
        return count;
    }
    public int reversePairs2(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return 0;
        }

        int[] copy = new int[len];

        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }

        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    //nums[left..right] 计算逆序对个数并且排序 
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        // 如果整个数组已经有序，则无需合并，注意这里使用小于等于
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    // nums[left..mid] 有序，nums[mid + 1..right] 有序
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {
            // 有下标访问，得先判断是否越界
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意：这里是 <= ，写成 < 就不对，请思考原因
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                
                // 在 j 指向的元素归并回去的时候，计算逆序对的个数，只多了这一行代码
                count += (mid - i + 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        InversionCountArray inver = new InversionCountArray();
        int[] arr0 = {20, 1};
        int[] arr1 = {1, 20, 6, 4, 5};
        int[] arr2 = {2, 4, 1, 3, 5};
        int[] arr3 = {7,5,6,4};
        System.out.println(inver.reversePairs(arr0));  // 1
        System.out.println(inver.reversePairs(arr1));  // 5
        System.out.println(inver.reversePairs(arr2));  // 3
        System.out.println(inver.reversePairs(arr3));  // 5
    }
}