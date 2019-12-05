package dp.seq;

import java.util.*;

/**
 * Title:
 * Desc:
 * Created by Myth-Lab on 11/25/2019
 */
public class P139WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> map = new HashMap<>();
        Set<String> set = new HashSet<>(wordDict);
        for (String word : wordDict) {
            map.put(word, true);
        }
        return memSearch3(s, map);
    }
    // 记忆化搜索(5%时间)
    public Boolean memSearch(String s, Map<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);
        for (int i = 0; i < s.length(); i++) {
            String split1 = s.substring(0, i);
            String split2 = s.substring(i);
            if (split1.length() != 0 && split2.length() != 0) {
                if (memSearch(split1, map) && memSearch(split2, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }
    // 修改版的记忆化搜索
    public Boolean memSearch2(String s, Map<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);
        for (int i = 0; i < s.length(); i++) {
            String split1 = s.substring(0, i);
            String split2 = s.substring(i);
            if (split1.length() != 0 && split2.length() != 0) {
                if (memSearch2(split1, map) && map.containsKey(split2)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }
    public Boolean memSearch3(String s, Map<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);
        for (int i = 0; i < s.length(); i++) {
            String split1 = s.substring(0, i);
            String split2 = s.substring(i);
            if (split1.length() != 0 && split2.length() != 0) {
                if (map.containsKey(split1) && memSearch3(split2, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }
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

    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("leet", "code");
        String s = "catsandog";
        List<String> wordDict2 = Arrays.asList("cats", "dog", "san", "and", "cat");
        String s2 = "catsandog";
        P139WordBreak p139 = new P139WordBreak();
        System.out.println(p139.wordBreak(s2, wordDict2));
    }
}
