package dp.seq.sub_seq;

/**
 * Title: 53. 最大子序和
 * Desc: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * Created by Myth-Lab on 11/20/2019
 */
public class P53MaximumSubarray {
    // 本题也是一个动态规划问题，其中curSum就保存了之前的状态
    public int maxSubArray(int[] nums) {
        // 注意初值
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curSum + nums[i] < nums[i]) {
                curSum = nums[i];
            } else {
                curSum = curSum + nums[i];
            }
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
