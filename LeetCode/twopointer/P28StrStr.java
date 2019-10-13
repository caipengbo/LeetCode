package twopointer;

import java.util.HashMap;

/**
 * Title: 模式匹配
 * Desc: 经典算法 KMP, Sunday
 * Created by Myth-Lab on 10/13/2019
 */
public class P28StrStr {
    // Sunday算法：如果当前窗口不匹配，比较窗口下一个字符串；下一个字符串在shift数组中的位置，就是窗口要偏移的距离
    // 先计算shift数组 every char : len(needle) - max(char)   otherwise: len+1
    public int strStr(String haystack, String needle) {
        // 使用 HashMap 实现shift数组
        HashMap<Character, Integer> shiftMap = new HashMap<>();
        int len = needle.length();
        for (int i = 0; i < len; i++) {
            Character character = needle.charAt(i);
            if (!shiftMap.containsKey(character)) {
                for (int j = len-1; j >= 0; j--) {
                    if (needle.charAt(j) == character) {
                        shiftMap.put(character, len-j);
                        break;
                    }
                }
            }
        }
        int p =  0;
        while (p + len <= haystack.length()) {
            int i;
            for (i = 0; i < len; i++) {
                if (haystack.charAt(p+i) != needle.charAt(i)) break;
            }
            if (i == len) return p;
            else if (p+len == haystack.length()) return -1;
            else p += shiftMap.getOrDefault(haystack.charAt(p+len), len+1);
        }
        return -1;
    }

    public static void main(String[] args) {
        P28StrStr p28 = new P28StrStr();
        System.out.println(p28.strStr("hello", "ll"));
        System.out.println(p28.strStr("substring searching", "ch"));
        System.out.println(p28.strStr("substring searching", "search"));
    }
}
