package dp.knapsack;

import java.util.Arrays;

/**
* Title: 518. 零钱兑换2（完全背包的零钱兑换）—— 有多少种可能
* Desc: 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。
* Created by Myth on 12/19/2019 in VSCode
*/

public class P518CoinChange2 {
    public int change(int amount, int[] coins) {
        // if (amount == 0) return 0;
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 0; i <= amount; i++) {
                if(i >= coin) dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }
    public static void main(String[] args) {
        P518CoinChange2 p518 = new P518CoinChange2();
        int[] coins = {1, 2, 5};
        int[] coins2 = {10};
        System.out.println(p518.change(10, coins2));
    }
}