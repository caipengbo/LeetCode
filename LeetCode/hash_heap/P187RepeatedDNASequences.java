package hash_heap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 187. 重复的DNA序列
 * Desc:
 * Created by Myth-Lab on 11/7/2019
 */
public class P187RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences1(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i+10 <= s.length(); i++) {
            String seq = s.substring(i, i+10);
            map.put(seq, map.getOrDefault(seq, 0)+1);
        }
        List<String> ret = new LinkedList<>();
        for (String seq : map.keySet()) {
            if (map.get(seq) > 1) ret.add(seq);
        }
        return ret;
    }
}
