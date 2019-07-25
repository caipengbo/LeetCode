package search;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 51. N皇后问题
 * Desc: N个皇后放置在 n X n 的棋盘上，不能同行， 不能同列，不能同对角线
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 难点：1. 放置皇后的顺序，按行放置or按列放置   2. 如何判断 该位置是否可以放置 皇后
 * Created by Myth on 7/25/2019
 */
public class P51NQueens {

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> ans = new ArrayList<List<String>>();
        backtracking(board, 0, ans);
        return ans;
    }
    private void backtracking(char[][] board, int col, List<List<String>> ans) {
        if (col == board.length) {
            ans.add(generateAns(board));
            return;
        }
        // 从左往右 按列放置 皇后
        for (int i = 0; i < board.length; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 'Q';
                backtracking(board, col+1, ans);
                board[i][col] = '.';
            }
        }
    }
    // （x,y） 位置是否可以放置 皇后
    // 主对角线: 有 行号 - 列号 = 常数
    // 次对角线有 行号 + 列号 = 常数
    private boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) { // 按列放，所以只要求 <y 的列没被放置即可
                if (board[i][j] == 'Q' && (x-y == i-j || x+y == i+j || x == i)) {
                    return false;
                }
            }
        }
        return true;
    }
    private List<String> generateAns(char[][] board) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            ans.add(new String(board[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        // 输入4
        // 输出: [
        // [".Q..",  // 解法 1
        //  "...Q",
        //  "Q...",
        //  "..Q."],

        // ["..Q.",  // 解法 2
        //  "Q...",
        //  "...Q",
        //  ".Q.."]
        //]
        //
        P51NQueens p51 = new P51NQueens();
        System.out.println(p51.solveNQueens(4));
    }
}
