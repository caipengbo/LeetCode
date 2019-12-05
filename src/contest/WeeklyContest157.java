package contest;

import java.util.Arrays;

/**
 * Title: 第157场周赛
 * Desc:
 * Created by Myth-PC on 2019-10-06
 */
public class WeeklyContest157 {
    // 判断奇偶，谁的数目小就返回谁的数目
    public int minCostToMoveChips(int[] chips) {
        int odd= 0, even = 0;
        for (int i = 0; i < chips.length; i++) {
            if (chips[i] % 2 == 0) odd++;
            else even++;
        }
        return Math.min(odd, even);
    }
    //[-4,-3,0,3,4,6,7]
    public int longestSubsequence(int[] arr, int difference) {
        int[] res = new int[arr.length];
        int ret = 1;
        for (int i = arr.length-1; i >= 0; i--) {
            res[i] = 1;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] + difference == arr[j]) {
                    res[i] = res[j]+1;
                    break;
                }
            }
            ret = Math.max(ret, res[i]);
        }
        return ret;
    }
    private int gold = 0;
    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                backtracking(grid, i, j, 0);
            }
        }
        return gold;
    }
    private void backtracking(int[][] grid, int i, int j, int sum) {
        int num = grid[i][j];
        if (num == 0) {
            gold = Math.max(gold, sum);
            return;
        }
        sum += num;
        grid[i][j] = 0;
        if (i > 0) backtracking(grid, i-1, j, sum);
        if (j > 0) backtracking(grid, i, j-1, sum);
        if (i < grid.length - 1) backtracking(grid, i+1, j, sum);
        if (j < grid[0].length - 1) backtracking(grid, i, j+1, sum);
        grid[i][j] = num;
    }
}
