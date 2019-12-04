package dp.twoseq;

/**
 * Title: 712. 两个字符串的最小ASCII删除和
 * Desc: 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。所有字符串中的字符ASCII值在[97, 122]之间。
 * Created by Myth-Lab on 12/4/2019
 */
public class P712MinimumASCIIDeleteSumForTwoStrings {
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        }
        for (int i = 1; i <= m; i++) {
            dp[0][i] = dp[0][i-1] + s2.charAt(i-1);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
            }
        }
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= m; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        P712MinimumASCIIDeleteSumForTwoStrings p712 = new P712MinimumASCIIDeleteSumForTwoStrings();
        p712.minimumDeleteSum("delete", "leet");
    }
}
