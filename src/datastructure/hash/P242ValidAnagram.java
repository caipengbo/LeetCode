package datastructure.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 242. 有效的字母异位词
 * Desc: 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * Created by Myth-Lab on 10/18/2019
 */
public class P242ValidAnagram {
    // 方法1：排序返回相等的串
    // 方法2：使用数组计数（ASCII码）只包含小写字母
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
    // 方法3：Unicode 时候
    public boolean isAnagram3(String s, String t) {
        if(t.length() != s.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            if(!map.containsKey(t.charAt(i))) return false;
            int val = map.get(t.charAt(i)) - 1;
            if (val <= 0) map.remove(t.charAt(i));
            else map.put(t.charAt(i), val);
        }
        return map.size() == 0;
    }
}
