package dp.partition;

/**
* Title: 322. 零钱兑换
* Desc: 
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

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount+1];

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) dp[coins[i]] = 1;
        }
        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                } 
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        P322CoinChange p322 = new P322CoinChange();
        int[] coins = {1, 2, 5};
        int[] coins2 = {2};
        System.out.println(p322.coinChange(coins, 12));
        System.out.println(p322.coinChange(coins2, 3));
    }
}