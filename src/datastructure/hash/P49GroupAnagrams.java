package datastructure.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 49. 字母异位词分组
 * Desc: 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * Created by Myth-Lab on 10/18/2019
 */
public class P49GroupAnagrams {
    // 难点：如何分类？
    private boolean isAnagrams(int[] strHash1, int[] strHash2) {
        for (int i = 0; i < 26; i++) {
            if (strHash1[i] != strHash2[i]) return false;
        }
        return true;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;
        List<List<String>> ret = new LinkedList<>();
        if (len == 0) return ret;
        int[][] strHashs = new int[len][26];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                strHashs[i][strs[i].charAt(j)-'a']++;
            }
        }
        List<Integer> retHeadIndexs = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            boolean added = false;
            for (int j = 0; j < ret.size(); j++) {
                int headIndex = retHeadIndexs.get(j);
                if (isAnagrams(strHashs[i], strHashs[headIndex])) {
                    ret.get(j).add(strs[i]);
                    added = true;
                    break;
                }
            }
            if (!added) {
                List<String> newGroup = new LinkedList<>();
                newGroup.add(strs[i]);
                ret.add(newGroup);
                retHeadIndexs.add(i);
            }
        }
        return ret;
    }
    // 使用hash
    public List<List<String>> groupAnagrams2(String[] strs) {
        int len = strs.length;
        List<List<String>> ret = new LinkedList<>();
        if (len == 0) return ret;
        // 类似于桶排序
        int[][] strHashs = new int[len][26];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                strHashs[i][strs[i].charAt(j)-'a']++;
            }
        }
        // key代表出现过的单词
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            // 重点  Hash的Key
            StringBuilder sb = new StringBuilder("");
            for (int j = 0; j < 26; j++) {
                sb.append(strHashs[i][j]);  // 最好加个-隔开每个单词的数目
            }
            String key = sb.toString();
            if (hashMap.containsKey(key)) {
                hashMap.get(key).add(strs[i]);
            } else {
                List<String> newList = new LinkedList<String>();
                newList.add(strs[i]);
                hashMap.put(key, newList);
            }
        }
        for (String key : hashMap.keySet()) {
            ret.add(hashMap.get(key));
        }
        return ret;
    }

    public static void main(String[] args) {
        P49GroupAnagrams p49 = new P49GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(p49.groupAnagrams2(strs));
    }
}
