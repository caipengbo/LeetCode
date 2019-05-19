import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Title:
 * Desc:
 * Created by Myth on 5/12/2019
 */


public class WeeklyContest137 {
    public static int lastStoneWeight(int[] stones) {
        int len = stones.length;
        if (len == 1) return stones[0];
        if (len == 2) return (Math.abs(stones[1] - stones[0]));
        Arrays.sort(stones);
        while (stones[len-2] != 0) {
            int diff = stones[len-1] - stones[len-2];
            if (diff == 0) {
                stones[len-1] = 0;
                stones[len-2] = 0;
            } else {
                stones[len-1] = diff;
                stones[len-2] = 0;
            }

            Arrays.sort(stones);
        }
        return stones[len-1];
    }
    public static String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (!stack.empty() && stack.peek() == S.charAt(i)) stack.pop();
            else stack.push(S.charAt(i));
        }
        int size = stack.size();
        StringBuilder s = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            s.append(stack.peek());
            stack.pop();
        }
        return s.reverse().toString();
    }
    // 超时！！！
    // 去除相邻重复
    public static String removeCore(String S) {
        List<Integer> dupIndex = new ArrayList<>();
        String retStr = "";
        int len = S.length();
        int cur = 0;
        int tail = 0;
        while (cur < len - 1) {
            if (S.charAt(cur) == S.charAt(cur+1)) {
                tail = cur;
                while (tail < len && S.charAt(tail) == S.charAt(cur)) {
                    dupIndex.add(tail);
                    tail++;
                }
                cur = tail;
            } else {
                cur++;
            }
        }
        int i = 0, j = 0;
        int dupLen = dupIndex.size();
        while (i < len) {
            if (j < dupLen && i != dupIndex.get(j)) {
                retStr += S.charAt(i);
            } else if (j == dupLen) {
                retStr += S.charAt(i);
            } else {
                j++;
            }
            i++;
        }
        return retStr;
    }

    // 去除相邻两个重复的元素
    public static String removeCore2(String S) {
        List<Integer> dupIndex = new ArrayList<>();
        String retStr = "";
        int len = S.length();
        int cur = 0;
        while (cur < len - 1) {
            if (S.charAt(cur) == S.charAt(cur+1)) {
                dupIndex.add(cur);
                dupIndex.add(cur+1);
                cur = cur+2;
            } else {
                cur++;
            }
        }
        int i = 0, j = 0;
        int dupLen = dupIndex.size();
        while (i < len) {
            if (j < dupLen && i != dupIndex.get(j)) {
                retStr += S.charAt(i);
                i++;
            } else if (j == dupLen) {
                retStr += S.charAt(i);
                i++;
            } else {
                i++;
                j++;
            }
        }
        return retStr;
    }

    public static void main(String[] args) {
        int[] stones = {3,7,2};
        // int[] stones = {2,7,4,1,8,1};
        // WeeklyContest137.lastStoneWeight(stones);
        System.out.println(WeeklyContest137.removeDuplicates("abbaca"));
        // System.out.println(WeeklyContest137.removeCore("abbaca"));
    }
}
