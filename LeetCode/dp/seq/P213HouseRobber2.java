package dp.seq;

import java.util.Arrays;

/**
 * Title: 213. 打家劫舍 II
 * Desc: 和198的区别在于本题是首尾相连的
 * Created by Myth-Lab on 11/22/2019
 */
public class P213HouseRobber2 {
    // 分成两个问题：不偷第一个，不偷最后一个
    // 难点：如何转化成两部分？ nums(0:]  nums[:n-1)
    // 和第198题一样，本次使用滚动数组
    public int robOne(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int pre = 0, cur = nums[0], temp;
        for (int i = 1; i < n; i++) {
            temp = pre;
            pre = cur;
            cur = Math.max(temp+nums[i], cur);
        }
        return cur;
    }
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        return Math.max(robOne(Arrays.copyOfRange(nums, 1, n)), robOne(Arrays.copyOfRange(nums, 0, n-1)));
    }
}
