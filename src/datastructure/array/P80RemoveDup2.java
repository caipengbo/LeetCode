package datastructure.array;



/**
* Title: 80. 删除重复元素2
* Desc: 排序数组，使得每个元素最多出现两次
* Created by Myth-MBP on 10/08/2020 in VSCode
*/
public class P80RemoveDup2 {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i-2]) nums[i++] = n;
        }
        return i;
    }
}