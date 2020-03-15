package twopointer;

/**
* Title: 27. 移除元素
* Desc:  原地移除元素
* Created by Myth-MBP on 15/03/2020 in VSCode
*/

public class P27RemoveElement {
    // 该题虽然很简单，但是要考虑的情况很多： 要移除的元素很多？ 很少？ 
    // 1. 要移动的元素很少的时候，就两个前向指针即可

    // 2. 当要移动的很多时候
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0, j = nums.length-1;
        while (i <= j) {
            while (i <= j && nums[i] != val) i++;
            while (i <= j && nums[j] == val) j--;
            
            if (j > i) nums[i++] = nums[j--];
        }
        // if (nums[i] == val) return i;
        return j+1;
    }
    // 2.2 只和最后一个元素交换
    public int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0, n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n-1];
                n--;
            } else {
                i++;
            }
        }
        // if (nums[i] == val) return i;
        return n;
    }

    public static void main(String[] args) {
        // {2,2,2}  2
        // {1} 1
        P27RemoveElement p27 = new P27RemoveElement();
        int[] nums = {2, 2, 2};
        int[] nums2 = {1};
        System.out.println(p27.removeElement(nums, 2));
        System.out.println(p27.removeElement(nums2, 1));
    }
}