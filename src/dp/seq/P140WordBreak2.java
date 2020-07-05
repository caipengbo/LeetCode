package dp.seq;

import java.util.*;

/**
 * Title: 140. 单词拆分2（此题使用 记忆化递归更方便一些）
 * Desc: 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，
 * 使得句子中所有的单词都在词典中。返回所有这些可能的句子。
    输入:
        s = "pineapplepenapple"
        wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
        输出:
        [
          "pine apple pen apple",
          "pineapple pen apple",
          "pine applepen apple"
        ]
    解释: 注意你可以重复使用字典中的单词。
 * Created by Myth-Lab on 11/25/2019
 */
public class P140WordBreak2 {
    // 难点：如何保存多种字符串
    // 记忆化 DFS
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordDict);
        return memSearch(s, map, wordSet);
    }
    public List<String> memSearch2(String s, Map<String, List<String>> map, Set<String> wordSet) {
        if (map.containsKey(s)) return map.get(s);
        List<String> res = new LinkedList<>();
        // 递归的结束条件
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordSet) {
            if (s.startsWith(word)) {
                String subString = s.substring(word.length());

                List<String> subRes =  memSearch2(subString, map, wordSet);
                for (String sub : subRes) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);  // 为空时需要特殊处理
                }
            
            }
        }
        map.put(s, res);
        return res;
    }
    public List<String> memSearch(String s, Map<String, List<String>> map, Set<String> wordSet) {
        if (map.containsKey(s)) return map.get(s);
        List<String> res = new LinkedList<>();
        // 递归的结束条件
        if (s.length() == 0) {
            res.add("");
        }
        // 特殊情况
        if (wordSet.contains(s)) res.add(s); 

        for (int i = 0; i < s.length(); i++) {
            String split1 = s.substring(0, i);
            String split2 = s.substring(i);
            if (wordSet.contains(split1)) {
                List<String> subRes =  memSearch(split2, map, wordSet);
                for (String sub : subRes) {
                    res.add(split1 + (sub.isEmpty() ? "" : " ") + sub);  // 为空时需要特殊处理
                }
            }
        }
        map.put(s, res);
        return res;
    }
    public static void main(String[] args) {
        P140WordBreak2 p140 = new P140WordBreak2();
        List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(p140.wordBreak("pineapplepenapple", wordDict));
        // System.out.println(p140.wordBreak("pineapple", wordDict));
    }
}