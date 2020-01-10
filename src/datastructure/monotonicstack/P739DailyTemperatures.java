package datastructure.monotonicstack;

import java.util.Stack;

/**
 * Title: 
 * Desc: 
 * Created by Myth on 01/10/2020 in VSCode
 */

public class P739DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int n = T.length, j;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            while(!stack.empty() && T[stack.peek()] < T[i]) {
                j = stack.pop();
                ret[j] = i-j;
            }
            stack.push(i);
        }
        return ret;
    }
}