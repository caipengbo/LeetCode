package dp.twoseq;

/**
 * Title:  583. 两个字符串的删除操作
 * Desc: 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * Created by Myth-Lab on 12/4/2019
 */
public class P583DeleteOperationforTwoStrings {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
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
        P583DeleteOperationforTwoStrings p583 = new P583DeleteOperationforTwoStrings();
        p583.minDistance("sea", "eat");
    }
}
