package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title:  正方形数组的数目
 * Desc: 给定一个非负整数数组 A，如果该数组每对相邻元素之和是一个完全平方数，则称这一数组为正方形数组。
 * 返回 A 的正方形排列的数目。两个排列 A1 和 A2 不同的充要条件是存在某个索引 i，使得 A1[i] != A2[i]。
 * Created by Myth on 7/19/2019
 */
public class P996SquarefulArraysPermutation {
    private int ans = 0;
    private boolean isSquare(int number) {
        int a = (int) Math.sqrt(number);
        return a*a == number;
    }
    private void backtracking(int[] A, int count, boolean[] used, List<Integer> cur) {
        if (count == A.length) {
            ans++;
            return;
        }
        for (int i = 0; i < A.length; i++) {
            // 仍然是排列那一套
            if (i > 0 && A[i] == A[i-1] && !used[i-1]) continue;
            if (!used[i]) {
                if (cur.size() == 0 || isSquare(A[i]+cur.get(cur.size()-1))) {
                    cur.add(A[i]);
                    used[i] = true;
                    backtracking(A, count+1, used, cur);
                    used[i] = false;
                    cur.remove(cur.size()-1);
                }
            }
        }
    }
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A); // 涉及到去重
        boolean[] used = new boolean[A.length];
        backtracking(A, 0, used, new ArrayList<>());
        return ans;
    }

    public static void main(String[] args) {
        P996SquarefulArraysPermutation p996 = new P996SquarefulArraysPermutation();
    }
}
