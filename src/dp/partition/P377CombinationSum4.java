package dp.partition;

import java.util.Arrays;

/**
* Title: 377. 组合总和（和322换零钱基本一致）
* Desc: 
* Created by Myth on 12/13/2019 in VSCode
*/

public class P377CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        for (int num : nums) {
            if(num <= target) dp[num]++;
        }
        for (int i = 0; i <= target; i++) {
            // System.out.println(i + "------");
            for (int num : nums) {
                if(i+num <= target) {
                    dp[i+num] += dp[i];
                }
                // System.out.println(Arrays.toString(dp));
            } 
        }
        // System.out.println(Arrays.toString(dp));
        return dp[target];
    }
    public int combinationSum42(int[] nums, int target) {
        int[] dp = new int[target+1];
        // for (int num : nums) {
        //     if(num <= target) dp[num]++;
        // }
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            // System.out.println(i + "------");
            for (int num : nums) {
                if(num <= i) {
                    dp[i] += dp[i-num];
                }
                // System.out.println(Arrays.toString(dp));
            } 
        }
        System.out.println(Arrays.toString(dp));
        return dp[target];
    }
    public static void main(String[] args) {
        P377CombinationSum4 p377 = new P377CombinationSum4();
        int[] nums = {1, 2, 3};
        p377.combinationSum42(nums, 4);
    }
}