package datastructure.hash;

/**
* Title: 3. 无重复字符的最长子串
* Desc: 双指针、滑动窗口 + Hash
* Created by Myth on 12/27/2019 in VSCode
*/

public class P3LongestStringWithoutRepeating {
    public int lengthOfLongestSubstring(String s) {
        int[] dic = new int[256];
        int p = 0, q = 0, n = s.length();
        int max = 0;
        while (p <= q && p < n) {
            if (q < n && dic[s.charAt(q)] == 0) {
                dic[s.charAt(q)]++;
                q++;
            } else {
                dic[s.charAt(p)]--;
                p++;
            }
            max = Math.max(max, q-p);
        }
        return max;
    }
    public static void main(String[] args) {
        P3LongestStringWithoutRepeating p3 = new P3LongestStringWithoutRepeating();
        System.out.println(p3.lengthOfLongestSubstring("ababac"));
    }
}