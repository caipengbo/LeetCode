package twopointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Title: 3. 无重复字符的最长子串
 * Desc: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 注意区分子串与子序列区别
 * Created by Myth-PC on 2019-10-06
 */
public class P3LongestSubstringWithoutRepeatingCharacters {
    // [i, j), 无重复的时候j++, 有重复的时候i++
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;
        int ret = 0;
        while (j < s.length() && i <= j) {
            // 是否存在重复字符
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                ret = Math.max(ret, j-i);  // 注意此处没+1，因为j先自增了
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return ret;
    }
    // 改进后的滑动窗口:
    //如果 s[j]在 [i,j) 范围内有与 j'重复的字符，我们不需要逐渐增加 i
    // 我们可以直接跳过 [i，j']范围内的所有元素，并将 i 变为 j' + 1
    // 使用HashMap -> 存在重复字符，并记录位置
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0, ret = 0;
        while (j < s.length() && i <= j) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
            }
            // 保存当前字符的下一个位置
            map.put(s.charAt(j), j+1);
            j++;
            ret = Math.max(ret, j-i);
        }
        return ret;
    }
}
