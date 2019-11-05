package math;

/**
 * Title:  279. 完全平方数
 * Desc: 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * Created by Myth-Lab on 11/5/2019
 */
public class P279PerfectSquares {
    // 1. 可以转化成图的问题：数字之间如果 相差 平方数，那么 数字之间存在边，问题就变成了求最短路径问题
    // 2. 搜索(DFS -> 记忆化DFS)
    public int numSquares(int n) {
        int[] mem = new int[n+1];
        return dfs(n, mem);
    }
    // 普通DFS（TLE）
    public int dfs(int n) {
        int sq = (int)Math.sqrt(n);
        if (sq * sq == n) return 1;  // n是sqrt,返回 1
        int min = Integer.MAX_VALUE;
        for (int i = 1; i * i < n; i++) {
            min = Math.min(min, dfs(n - i * i)+1);
        }
        return min;
    }
    // 记忆化搜索（自上而下）
    public int dfs(int n, int[] mem) {
        int sq = (int)Math.sqrt(n);
        if (sq * sq == n) {
            mem[n] = 1;
            return 1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i * i < n; i++) {
            int next = n-i*i;
            if (mem[next] != 0) min = Math.min(min, mem[next]+1);  // 添加记忆化搜索
            else min = Math.min(min, dfs(next, mem)+1);
        }
        mem[n] = min;
        return min;
    }
    // 3. 动态规划（自下而上）
    public int numSquares2(int n) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        P279PerfectSquares p279 = new P279PerfectSquares();
        System.out.println(p279.numSquares(12));
    }
}
