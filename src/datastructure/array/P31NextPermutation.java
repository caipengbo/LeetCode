package datastructure.array;

import java.util.Arrays;

/**
 * Title: 31. 下一个排列 
 * Desc: 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
 * Created by Myth-MBP on 12/07/2020 in VSCode
 */
public class P31NextPermutation {
    // 下一个排列的特点：前面找个小的数，后面找个大的数，然后交换
        // 然后新的大数位置后面要从小到大排列
        // 前面小的保证尽可能的靠后，后面大的数尽可能的小
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        
        int i = 0;
        // 注意此时的i为何是从后面开始找的？？
        for (i = n-1; i > 0;i--) {
            // i-1位置是左边较小的数，
            if (nums[i-1] < nums[i]) {
                // 找右侧比i-1位置大的 最小的数（注意右侧是降序排列的）
                int j = i;
                while (j < n && nums[i-1] < nums[j]) {
                    j++;
                }
                swap(nums, i-1, j-1);
                break;
            }
        }
        // Arrays.sort(nums, i, n);
        // 保证右侧是从小到大的(右侧逆序即可)
        reverse(nums, i);
    }
    
    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length-1;
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
    public void nextPermutation2(int[] nums) {
        int n = nums.length;
        // 前面找个小的数，后面找个大的数，然后交换
        // 然后新的大数位置后面要从小到大排列
        // 前面小的保证尽可能的靠后，后面大的数尽可能的小
        int i = 0;
        for (i = n-1; i > 0;i--) {
            if (nums[i-1] < nums[i]) {
                // 找右侧比i-1位置大的 最小的数
                int minIndex = i;
                for (int j = i; j < n; j++) {
                    if (nums[i-1] < nums[j] && nums[j] < nums[minIndex]) {
                        minIndex = j;
                    }
                }
                swap(nums, i-1, minIndex);
                break;
            }
        }
        Arrays.sort(nums, i, n);
        // reverse(nums);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void reverse(int[] nums) {
        int i = 0, j = nums.length-1;
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}