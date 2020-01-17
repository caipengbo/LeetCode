package divideconquer;

import java.util.PriorityQueue;

/**
* Title: 215. Kth Largest Element in an Array
* Desc: 使用堆的方法见datastructure.heap包，本解法使用分治，快排的partition函数
* Created by Myth on 01/16/2020 in VSCode
*/

public class P215KthLargestElementInArray {
    // 大到小排列的 第 k-1 位置
    public int findKthLargest(int[] nums, int k) {
        
        return 0;
    }
    public int partition(int[] nums, int l, int r) {
        if (l > r) return -1;
        int pivot = nums[l];
        while (l < r) {
            while (l < r && nums[r] <= pivot) r--;
            nums[r] = nums[l];
            while (l < r && nums[l] >= pivot) l++;
            nums[l] = nums[r];
        }
        nums[l] = pivot;
        return l;
    }

    public static void main(String[] args) {
        P215KthLargestElementInArray p215 = new P215KthLargestElementInArray();
        int[] nums = {3,2,1,5,6,4};
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        p215.findKthLargest(nums2, 4);
    }
}
