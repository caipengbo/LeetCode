package twopointer.slidewindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

/**
 * Title: 76. 最小覆盖子串
 * Desc: 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出: 包含 T 所有字母的最小子串。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * Created by Myth-Lab on 10/14/2019
 */
public class P76MinimumWindowSubstring {
    // 第3题很像，滑动窗口法
    // 难点：如何快速检测是否包含T中所有的字母
    // 方法1,2都是统计整个滑动窗口，然后看和 T是否一致
    public String minWindow1(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        Map<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            targetMap.put(t.charAt(i), targetMap.getOrDefault(t.charAt(i), 0)+1);
        }
        int left = 0, right = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        windowMap.put(s.charAt(0), 1);
        String ret = "";
        int min = Integer.MAX_VALUE;
        boolean contain;
        while (right < s.length() && left <= right) {
            // 难点
            contain = true;
            for (Map.Entry<Character, Integer> entry : targetMap.entrySet()) {
                if (windowMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    contain = false;
                    break;
                }
            }
            if (contain) {
                if (min > (right-left+1)) {
                    min = right-left+1;
                    ret = s.substring(left, right+1);  // 不要记住ret
                }
                windowMap.put(s.charAt(left), windowMap.get(s.charAt(left))-1);
                left++;
            } else {
                if (right+1 < s.length()) windowMap.put(s.charAt(right+1), windowMap.getOrDefault(s.charAt(right+1), 0)+1);
                right++;
            }
        }
        return ret;
    }
    // 使用数组比上面的使用map要慢
    public String minWindow2(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        char[] tmap = new char[128];
        for (char ch : t.toCharArray()) tmap[ch]++;
        char[] wmap = new char[128];
        wmap[s.charAt(0)] = 1;
        int l = 0, r = 0, left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        boolean contain;
        while (right < s.length()) {
            contain = true;
            for (int k = 0; k < t.length(); k++) {
                if (wmap[t.charAt(k)] < tmap[t.charAt(k)]) {
                    contain = false;
                    break;
                }
            }
            if (contain) {
                if (min > (right-left)) {
                    min = right-left;
                    l = left;
                    r = right;
                }
                wmap[s.charAt(left++)]--;
            } else {
                if (right+1 < s.length()) wmap[s.charAt(right+1)]++;
                right++;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(l, r+1);
    }
    // 统计滑动窗口中匹配了多少个T中的字符
    public String minWindow3(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        Map<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            targetMap.put(t.charAt(i), targetMap.getOrDefault(t.charAt(i), 0)+1);
        }
        int left = 0, right = 0,  i = 0, j = 0;
        int count = t.length();
        int min = Integer.MAX_VALUE;
        while (j < s.length()) {
            if (targetMap.containsKey(s.charAt(j))) {
                targetMap.put(s.charAt(j), targetMap.get(s.charAt(j))-1);   // < 0 说明
                if (targetMap.get(s.charAt(j)) >= 0) count--;
            }
            while (count == 0) {
                if (min > (j-i)) {
                    min = j - i;
                    left = i;
                    right = j;
                }
                if (targetMap.containsKey(s.charAt(i))) {
                    targetMap.put(s.charAt(i), targetMap.get(s.charAt(i))+1);
                    if (targetMap.get(s.charAt(i)) > 0) count++;
                }
                i++;
            }
            j++;
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(left, right+1);
    }
    public String minWindow4(String s, String t) {
        int[] map = new int[128];
        // 后续会大于0的原因
        for (char c : t.toCharArray()) map[c]++;

        int count = t.length();
        int minLen = Integer.MAX_VALUE, l = 0, r = 0;
        int i = 0, j = 0;
        while (j < s.length()) {
            if (map[s.charAt(j++)]-- > 0) count--;
            while (count == 0) {
                if (j - i < minLen) {
                    minLen = j - i;
                    l = i;
                    r = j;
                }
                if (++map[s.charAt(i++)] > 0) count++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(l, r);
    }

    public static void main(String[] args) {
        P76MinimumWindowSubstring p76 = new P76MinimumWindowSubstring();
        System.out.println(p76.minWindow3("ADOBECODEBANC", "ABC"));
    }
}
