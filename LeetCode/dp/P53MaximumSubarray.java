package dp;

/**
 * Title: 53. 最大子序和
 * Desc:
 * Created by Myth-Lab on 11/20/2019
 */
public class P53MaximumSubarray {
    // 本题也是一个动态规划问题，其中curSum就保存了之前的状态
    public int maxSubArray(int[] nums) {
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
