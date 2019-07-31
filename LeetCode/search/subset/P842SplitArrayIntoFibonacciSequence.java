package search.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 842. 将数组拆分成斐波那契序列
 * Desc:  https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/
 * Created by Myth on 7/22/2019
 */
public class P842SplitArrayIntoFibonacciSequence {
    // 回溯（带布尔返回值），因为只要一个结果
    // 循环：从start 往后拆分
    // 剪枝：满足斐波那契数列条件、满足不以0开头（除非是0）
    // 结束：start==len
    private boolean backtracking(String S, int start, List<Integer> ans) {
        if (start == S.length() && ans.size() > 2) {
            return true;
        }
        for (int i = start; i < S.length(); i++) {
            String segment = S.substring(start, i+1);
            if (!"0".equals(segment) && segment.startsWith("0")) break;
            // 数值超过范围
            if (Long.parseLong(segment) > Integer.MAX_VALUE) break;
            if (isFibonacciSequence(Integer.valueOf(segment), ans)) {
                ans.add(Integer.valueOf(segment));
                if (backtracking(S, i+1, ans)) return true;
                ans.remove(ans.size()-1);
            }
        }
        return false;
    }
    // 判断是否能组成斐波那契序列
    private boolean isFibonacciSequence(Integer num, List<Integer> ans) {
        int size = ans.size();
        if (size < 2) return true;
        return (ans.get(size-2) + ans.get(size-1) == num);
    }
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans =  new ArrayList<>();
        backtracking(S, 0, ans);
        return ans;
    }

    public static void main(String[] args) {
        P842SplitArrayIntoFibonacciSequence p842 = new P842SplitArrayIntoFibonacciSequence();
        System.out.println(p842.splitIntoFibonacci("123456579"));
    }
}
