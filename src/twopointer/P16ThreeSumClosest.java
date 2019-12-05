package twopointer;

import java.util.Arrays;

/**
 * Title: 16. 最接近的三数之和
 * Desc: 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 * Created by Myth-Lab on 10/8/2019
 */
public class P16ThreeSumClosest {
    // 本题假定只有 一个答案，所以不用去重了
    // 和第15题思路一模一样
    public int threeSumClosest(int[] nums, int target) {
        // nums.length > 3
        Arrays.sort(nums);
        int ret = nums[0]+nums[1]+nums[2];
        for (int i = 0; i < nums.length-2; i++) {
            int j = i+1, k = nums.length-1, sum;
            while (j < k) {
                sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum-target) < Math.abs(ret-target)) ret = sum;
                if (sum == target) return target;
                else if (sum > target) k--;
                else j++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        P16ThreeSumClosest p16 =  new P16ThreeSumClosest();
        int[] nums = {-3,-2,-5,3,-4};
        System.out.println(p16.threeSumClosest(nums, -1));
    }
}
