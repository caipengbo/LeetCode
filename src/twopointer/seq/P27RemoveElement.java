package twopointer.seq;

/**
* Title: 27. 移除元素
* Desc:  给定一个数组，原地移除元素（分为很多情况）
* Created by Myth-MBP on 15/03/2020 in VSCode
*/

public class P27RemoveElement {
    // 该题虽然很简单，但是要考虑的情况很多： 要移除的元素很多？ 很少？ 
    // 1. 要删除的元素很多的时候，就两个前向指针即可
    public int removeElement1(int[] nums, int val) {
        int ans = 0;
        for(int num: nums) {
            if(num != val) {
                nums[ans] = num;
                ans++;
            }
        }
        return ans;
    }
    // 2. 当要保留的很多的时候，说明移除的少
    public int removeElement2(int[] nums, int val) {
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
    public int removeElement3(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0, n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                // 不找位置，全都移过去（代码简洁，效率略低于2）
                nums[i] = nums[--n];
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
        System.out.println(p27.removeElement1(nums, 2));
        System.out.println(p27.removeElement1(nums2, 1));
    }
}