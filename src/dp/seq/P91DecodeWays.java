package dp.seq;

/**
* Title:  91. 解码方法（剑指offer46题）
* Desc: 一条包含字母 A-Z 的消息通过以下方式进行了编码：
'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

* Created by Myth on 12/25/2019 in VSCode
*/

public class P91DecodeWays {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 1;
        if (s.charAt(0) == '0') return 0;
        dp[1] = 1;
        // 注意处理 0 
        for (int i = 2; i <= n; i++) {
            if (s.charAt(i-1)-'0' == 0) {
                if (s.charAt(i-2)-'0' == 0) return 0;  // 不能有两个连续的0
                if ((s.charAt(i-2)-'0')*10 <= 20) dp[i] = dp[i-2];
                else dp[i] = 0;  // 可省略
            } else if (s.charAt(i-2)-'0' == 0 || (s.charAt(i-2)-'0')*10+(s.charAt(i-1)-'0') > 26) {
                dp[i] = dp[i-1];
            } else {
                dp[i] = dp[i-1] + dp[i-2];
            }
        }
        // System.out.println(Arrays.toString(dp) );
        return dp[n];
    }
    public static void main(String[] args) {
        P91DecodeWays p91 = new P91DecodeWays();
        System.out.println(p91.numDecodings("30"));
    }
}