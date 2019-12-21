package dp.knapsack;

import java.util.Arrays;

/**
* Title: 322. 零钱兑换（完全背包问题）
* Desc: 每种硬币可以用无限次
* Created by Myth on 12/11/2019 in VSCode
*/

public class P322CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount+1];

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) dp[coins[i]] = 1;
        }
        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (dp[i] != 0 && coins[j] != Integer.MAX_VALUE && i + coins[j] <= amount) {
                    dp[i+coins[j]] = dp[i+coins[j]] != 0 ? Math.min(dp[i+coins[j]], dp[i] + 1) : dp[i] + 1;
                } 
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                } 
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    // 最终写法
    public int coinChange3(int[] coins, int amount) {
        int len = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = 0; i <= amount; i++) {
                if (coin <= i) dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        P322CoinChange p322 = new P322CoinChange();
        int[] coins = {1, 2, 5};
        int[] coins2 = {1};
        System.out.println(p322.coinChange3(coins, 11));  // 3
        System.out.println(p322.coinChange3(coins2, 2));  // -1
    }
}