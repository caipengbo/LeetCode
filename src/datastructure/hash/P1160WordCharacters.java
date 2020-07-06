package datastructure.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 1160. 拼写单词 
 * Desc: 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。

返回词汇表 words 中你掌握的所有单词的 长度之和。
 * 
 * Created by Myth-MBP on 17/03/2020 in VSCode
 */

public class P1160WordCharacters {
    // 如何高效的实现hash, 除了hashmap 
    public int countCharacters(String[] words, String chars) {
        int n = chars.length();
        Map<Character, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(chars.charAt(i), map.getOrDefault(chars.charAt(i), 0)+1);
        }
        int count = 0;
        boolean flag;
        for (String word : words) {
            int len = word.length();
            Map<Character, Integer> wordMap = new HashMap<>(len);
            flag = true;
            for (int i = 0; i < len; i++) {
                int times = wordMap.getOrDefault(word.charAt(i), 0)+1;
                if (!map.containsKey(word.charAt(i)) || times > map.get(word.charAt(i))) {
                    flag = false;
                    break;
                }
                wordMap.put(word.charAt(i), times);
            }
            if (flag) count += word.length();
        }
        return count;
    }
    // 高效精简版
    // 仅包含小写英文字母
    public int countCharacters2(String[] words, String chars) {
        int[] charsMap = new int[26];
        int n = words.length, len = chars.length();
        for (int i = 0; i < len; i++) {
            charsMap[chars.charAt(i)-'a']++;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += canForm(words[i], charsMap);
        }
        return count;
    }
    private int canForm(String word, int[] map) {
        int[] wordMap = new int[26];
        int len = word.length();
        for (int i = 0; i < len; i++) {
            wordMap[word.charAt(i)-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (wordMap[i] > map[i]) return 0;
        }
        return len;
    }

}