package datastructure.array;


/**
* Title: 189. 旋转数组
* Desc: 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
* Created by Myth-MBP on 07/07/2020 in VSCode
*/
public class P189RotateArray {
    // 原地算法
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (k >= n) k = k % n;
        if (k == 0) return;
        reverse(nums, 0, n-k-1);
        reverse(nums, n-k, n-1);
        reverse(nums, 0, n);
    }
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    private void swap(int[] nums, int i, int j) {
        if (i >= j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    
}