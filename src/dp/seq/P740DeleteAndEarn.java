package dp.seq;

/**
* Title: 740. 删除与获得点数(难点：如何变成打家劫舍问题)
* Desc: 打家劫舍（选择i,就不能选择相邻的）的变体
* Created by Myth on 12/29/2019 in VSCode
*/

public class P740DeleteAndEarn {
    // 通过计数变成 198题打家劫舍问题
    public int deleteAndEarn(int[] nums) {
        // 构建新数组T = O(n); S = O(max)
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        if (max == 0) return 0;
        int[] arr = new int[max+1];
        int[] dp = new int[max+1];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
        }
        dp[0] = 0;
        dp[1] = arr[1];
        for (int i = 2; i <= max; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i]*i);
        }
        return dp[max];
    }

}