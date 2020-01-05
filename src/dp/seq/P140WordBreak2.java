package dp.seq;

import java.util.*;

/**
 * Title: 140. 单词拆分2
 * Desc: 在139的基础之上，打印出所有的可能
 * Created by Myth-Lab on 11/25/2019
 */
public class P140WordBreak2 {
    // 难点：如何保存多种字符串
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>(wordDict);
        for (String word : wordDict) {
            map.put(word, true);
        }
        memSearch(s, map);

    }
    public void memSearch(String s, Map<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);
        for (int i = 0; i < s.length(); i++) {
            String split1 = s.substring(0, i);
            String split2 = s.substring(i);
            if (split1.length() != 0 && split2.length() != 0) {
                if (map.containsKey(split1) && memSearch(split2, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }
}