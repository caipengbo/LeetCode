package search.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 93. 复原IP地址（回溯）
 * Desc: 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * Created by Myth on 7/20/2019
 */
public class P93RestoreIPAddresses {
    // 该题可以看成如何分割串（分割完之后再split）
    // cur : 当前答案，以 String List的形式，最后再join成String形式 例如 [[255],[255],[111],[35]] -> 255.255.111.35
    // pos, 当前扫描到的s的位置， ans最终答案
    private void backtracking(String s, int pos, List<String> cur,  List<String> ans) {
        if (cur.size() >= 4) {
            if (pos == s.length()) ans.add(String.join(".", cur));
            return;
        }
        // 分割得到ip地址的一段后，下一段只能在长度1-3范围内选择
        for (int i = 1; i <= 3; i++) {
            if (pos+i > s.length()) break;
            String segment = s.substring(pos, pos+i);
            // 剪枝条件：不能以0开头，不能大于255
            if (segment.startsWith("0") && segment.length() > 1 || (i == 3 && Integer.parseInt(segment) > 255)) continue;
            cur.add(segment);
            // 注意此处传的参数
            backtracking(s, pos+i, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<String> restoreIpAddresses1(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans; 
    }
    // 
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new LinkedList<>();
        LinkedList<String> cur = new LinkedList<>();
        restoreIpAddresses(s, ans, cur, 0);
        return ans;
    }
    private void  restoreIpAddresses(String s, List<String> ans, LinkedList<String> cur, int pos) {
        if (cur.size() == 4) {
            if (pos == s.length()) {
                ans.add(String.join(".", cur));
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (pos+i > s.length()) break;
            String seg = s.substring(pos, pos+i);
            if ((i > 1 && seg.startsWith("0")) || Integer.valueOf(seg) > 255) break;
            cur.addLast(seg);
            restoreIpAddresses(s, ans, cur, pos+i);
            cur.removeLast();
        }
    }

    public static void main(String[] args) {
        P93RestoreIPAddresses p93 = new P93RestoreIPAddresses();
        String s = "25525511135";
        String s1 = "0000";
        String s2 = "010010";
        String s3 = "010010";
        System.out.println(p93.restoreIpAddresses(s2));
    }
}
