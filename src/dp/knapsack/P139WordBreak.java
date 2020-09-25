package dp.knapsack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Title: 139. 单词拆分（完全背包问题）  在dp.seq包下还有一个
 * Desc: 
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
    说明：
        拆分时可以重复使用字典中的单词
        你可以假设字典中没有重复的单词
 * Created by Myth on 12/19/2019 in VSCode
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