package datastructure.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 187. 重复的DNA序列
 * Desc: 所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，
 * 例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来查找目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 
 * 示例：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC", "CCCCCAAAAA"]
 * 
 * Created by Myth-Lab on 11/7/2019
 */
public class P187RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences1(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        // 滑动窗口 + Hash
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
