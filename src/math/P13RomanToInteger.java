package math;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 13. 罗马数字转整数
 * Desc:
 * Created by Myth-Lab on 10/31/2019
 */
public class P13RomanToInteger {
    public int romanToInt(String s) {
        // 处理特殊情况
        Map<String, Integer> lookup = new HashMap<String, Integer>() {{
            put("IV", 4);
            put("IX", 9);
            put("XL", 40);
            put("XC", 90);
            put("CD", 400);
            put("CM", 900);
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
        }};
        int ret = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            if (i+1 < len && lookup.containsKey(s.substring(i, i+2))) {
                ret += lookup.get(s.substring(i, i+2));
                i = i + 1;
            } else {
                ret += lookup.get(s.substring(i, i+1));
            }
        }
        return ret;
    }
}
