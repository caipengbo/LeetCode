# 背包问题
最原始的01背包问题
有一些物品每个只有一件，w[i]是重量，v[i]是价值，给一个可以装W的包，问可以装的最大价值是多少？

解决思路：
1. 使用二维DP， 背包容量作为横轴，每一个元素的下标竖轴（计算的时候要使用w[i]）
2. 数组（矩阵）中存储的是：价值（当前状态下可以装的物品的最大价值）
3. 赋值的过程，从上到下，从右到左，从上到下代表 前i个物品

例子：背包W=4，货物w为[1,1,2,2] 相应价值[1,2,4,5]

# 01背包

滚动数组，从后往前更新
```Java
// n个商品，背包W,  w商品重量数组   v价值数组
void knapsack01(int n, int W, int[] w, int[] v) {
    dp[W+1]
    // int[] count = new int[W+1]
    for (int i = 0; i < n; i++) {
        for (int j = W; j >= w[i]; j--) {
            // if (dp[j] <= dp[j-w[i]]+v) count[j] = count[j-w[j]]+1
            // 限制条件，如果有多个限制条件，多几个循环
            dp[j] = max(dp[j], dp[j-w[i]]+v)
        }
    }
    return dp[W]  // max(dp[N])
}
```
# 完全背包

滚动数组，从前往后更新
```Java
void knapsackComplete(w, v) {
    dp[W+1]
    // int[] count = new int[W+1]
    for (int i = 0; i < n; i++) {
        // 注意此处
        for (int j = w[i]; j <= W; j++) {
            // if (dp[j] <= dp[j-w[i]]+v) count[j] = count[j-w[j]]+1
            // 限制条件，如果有多个限制条件，多几个循环
            dp[j] = max(dp[j], dp[j-w[i]]+v)
        }
    }
    return dp[W]  // max(dp[N])
}
```