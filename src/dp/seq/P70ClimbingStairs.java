package dp.seq;

import java.util.Arrays;

/**
 * Title: 70. 爬楼梯(DP入门题)(704进阶题)
 * Desc: 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？注意：给定 n 是一个正整数。
 * Created by Myth-Lab on 11/13/2019
 */
public class P70ClimbingStairs {
    // f(0)=1 f(1) = 1; f(2)= 2;  f(x)=(x-1)+f(x-2): 是个斐波那契问题
    public int f1(int n) {
        if (n <= 1) return 1;
        return f1(n-1)+f1(n-2);
    }
    // 带记忆化（自顶向下）
    public int f2(int n, int[] mem) {
        if (n <= 1) return 1;
        if (mem[n] == 0) mem[n] = f2(n-1, mem)+f2(n-2, mem);
        return mem[n];
    }
    // 自底向上（DP）
    public int f3(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    // 自底向上（DP）+ 滚动数组（不需要保存整个数组，只保存末尾两个元素即可）
    public int f4(int n) {
        int dp1 = 1, dp2 = 1;
        int temp;
        for (int i = 2; i <= n; i++) {
            temp = dp1;
            dp1 = dp2;
            dp2 = temp + dp1;
        }
        return dp2;
    }
    public int climbStairs(int n) {
        return f4(n);
    }
    // ========不能连续两个2
    public static int f(int n) {
        int[][] dp = new int[n+1][2];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;  // 1
            dp[i][1] = 0;  // 2
        }
        for (int i = 2; i <= n; i++) {
            // 走一步
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-2][0];
        }
        return dp[n][0] + dp[n][1];
    }
    public static long f2(int n) {
        if (n == 0) return 0;
        long dp10 = 1, dp20 = 1, dp21 = 0;
        long temp1;
        
        for (int i = 2; i <= n; i++) {
            // 走一步
            temp1 = dp10;
            dp10 = dp20;
            dp20 = temp1 + dp20;
            dp21 = temp1;
        }
        return dp20+dp21;
    }
    public static long count(int step, boolean last_two) {
        if (step == 1 || step == 0) {
            return 1;
        }
        long res = count(step-1, false);
        if (!last_two) {
            res += count(step-2, true);
        }
        return res;
    }
    public static void main(String[] args) {

        for (int i = 0; i <= 10; i++) {
            if (count(i, false) != f(i)) {
                System.out.println(i);
            }
            
        }
        // System.out.println(f(1000));
        
    }
}
