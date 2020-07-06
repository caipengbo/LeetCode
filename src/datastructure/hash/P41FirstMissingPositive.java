package datastructure.hash;

/**
* Title: 41. 缺失的第一个正数
* Desc: 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
* 要求： 时间复杂度 O(N)  空间复杂度 O(1)
* Created by Myth-MBP on 10/05/2020 in VSCode
*/

public class P41FirstMissingPositive {
    // 数组长度为N，那么缺失的最小正整数一定在 [1, N+1] 之间
    // 使用排序时间复杂度不符合要求，使用Hash表，空间复杂度不满足要求

    // 这就需要一种类似于桶思想的Hash方法，不使用额外的Hash表，将原数组当做Hash表进行统计
    // 难点：如何将原数组当做Hash表，如何不借助额外的空间进行Hash映射

    // 思路：数字nums 应该放在nums-1这个下标的位置，如何把它放过去（或者如何做标记）
    // 如何检测那些是正确归位（或者哪些没被标记）
    
    // 方法1：放过去
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // 说明 i 应该放到 nums[i]-1 的位置
            while (nums[i] > 0 && nums[i] < n && nums[i]-1 != nums[nums[i]-1]-1) {
                swap(nums, i, nums[i]-1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != nums[i]-1) {
                return i+1;
            }
        }
        return n+1;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    // √ 方法2：做标记 （使用负号做标记）
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        // 处理小于0和 > n的数（让他们都变成n+1） 如何1 —— n都出现了，那么最后肯定是n+1
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0 || nums[i] > n) {
                nums[i] = n+1;
            }
        }
            
        for (int i = 0; i < n; i++) {
            // 使用-1 标记 i 应该放到 nums[i]-1 的位置
            if (nums[i] < n && nums[nums[i]-1] > 0) {
                nums[nums[i]-1] *= -1;
            }
        }
        // 检查
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i+1;
            }
        }
        return n+1;
    }
}