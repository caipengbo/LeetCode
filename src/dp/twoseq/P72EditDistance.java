package dp.twoseq;

/**
 * Title: 72. 编辑距离
 * Desc: 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

    插入一个字符
    删除一个字符
    替换一个字符

 * 画出二维表格更便于理解
 * 
     输入：word1 = "intention", word2 = "execution"
    输出：5
    解释：
    intention -> inention (删除 't')
    inention -> enention (将 'i' 替换为 'e')
    enention -> exention (将 'n' 替换为 'x')
    exention -> exection (将 'n' 替换为 'c')
    exection -> execution (插入 'u')

 * Created by Myth-Lab on 11/27/2019
 */
public class P72EditDistance {
    //
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length(), i, j;
        int[][] dp = new int[n+1][m+1];
        for (i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= m; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
            }
        }
        return dp[n][m];
    }
}
