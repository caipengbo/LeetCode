package divideconquer;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
/**
 * Title: 241. 为运算表达式设计优先级 
 * Desc: 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 *
 * Created by Myth-PC on 19/01/2020 in VSCode
 */
public class P241DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        // Map<String, List<Integer>> map = new HashMap<>();
        return partition(input);
    }
    // 每一个表达式有多种可能的结果
    public List<Integer> partition(String s) {
        // Only Number
        List<Integer> ret = new LinkedList<>();
        if (!s.contains("+") && !s.contains("-") && !s.contains("*")) {
            ret.add(Integer.parseInt(s));
            return ret;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*') {
                for (Integer left : partition(s.substring(0, i))) {
                    for (Integer right : partition(s.substring(i+1))) {
                        if (s.charAt(i) == '+') ret.add(left+right);
                        if (s.charAt(i) == '-') ret.add(left-right);
                        if (s.charAt(i) == '*') ret.add(left*right);
                    }
                }
            }
        }
        return ret;
    }
    // 记忆化
    public List<Integer> partitionMem(String s, Map<String, List<Integer>> map) {
        // Only Number
        if (map.containsKey(s)) return map.get(s);
        List<Integer> ret = new LinkedList<>();
        if (!s.contains("+") && !s.contains("-") && !s.contains("*")) {
            ret.add(Integer.parseInt(s));
            
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*') {
                    for (Integer left : partitionMem(s.substring(0, i), map)) {
                        for (Integer right : partitionMem(s.substring(i+1), map)) {
                            if (s.charAt(i) == '+') ret.add(left+right);
                            if (s.charAt(i) == '-') ret.add(left-right);
                            if (s.charAt(i) == '*') ret.add(left*right);
                        }
                    }
                }
            }
        }
        map.put(s, ret);
        return ret;
    }

    public static void main(String[] args) {
        P241DifferentWaysToAddParentheses p241 = new P241DifferentWaysToAddParentheses();
        System.out.println(p241.diffWaysToCompute("2*3-4*5"));
        System.out.println(p241.diffWaysToCompute("2-1-1"));
    }
}