package dp.knapsack;

import java.util.Arrays;

/**
* Title: 1155. 掷骰子的N种方法
* Desc: 剑指offer第60题, bei
* Created by Myth on 12/27/2019 in VSCode
*/

public class P1155NumberOfDiceRollsWithTargetSum {
    public int numRollsToTarget(int d, int f, int target) {
        int[] dp = new int[target+1];
        for (int i = 1; i <= f && i <= target; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < d; i++) {
            for (int j = target; j > 0; j--) {
                int sum = 0;
                for (int k = 1; k <= f && k <= j; k++) {
                    sum = (sum + dp[j-k]) % 1000000007;
                }
                dp[j] = sum;
            }
        }
        return dp[target];
    }
    public static void main(String[] args) {
        P1155NumberOfDiceRollsWithTargetSum p1155 = new P1155NumberOfDiceRollsWithTargetSum();
        
        System.out.println(p1155.numRollsToTarget(6, 30, 50));
    }
}