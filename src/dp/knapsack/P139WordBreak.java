package dp.knapsack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Title: 139. 单词拆分（完全背包问题） 
 * Desc: Created by Myth on 12/19/2019 in VSCode
 */

public class P139WordBreak {
    // 在上面代码的基础上改成 DP（状态是前缀）
    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];   // s.sub(0, i)是否符合要求
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {  // s.sub(0, i)
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {  // 前部分符合 && 后部分符合
                    dp[i] = true;  // 当前子串符合
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}