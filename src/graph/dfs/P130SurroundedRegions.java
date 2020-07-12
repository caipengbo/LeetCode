package graph.dfs;


/**
* Title: 130. 被围绕的区域
* Desc: 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
* Created by Myth-MBP on 12/07/2020 in VSCode
*/

public class P130SurroundedRegions {
    
     // 难点：修改原数组，不引入外部访问数组，如果要记录访问状态的话需要记录多种状态，boolean无法满足，空间复杂度较高
     // 对于和边界相连的O使用#进行记录，最终的O就是与边界不相连的，将其替换为X
     public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && (i == 0 || j == 0 || i == n-1 || j == m-1)) {
                    dfs(board, i, j, m, n);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    // 对边界进行dfs,是否和边界字母O相连  相连返回true
    private void dfs(char[][] board, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j <= 0 || j >= n || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        
        board[i][j] = '#';
        
        dfs(board, i-1, j, m, n);
        dfs(board, i+1, j, m, n);
        dfs(board, i, j-1, m, n);
        dfs(board, i, j+1, m, n);
    }
    public static void main(String[] args) {
        char[][] board = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        P130SurroundedRegions p130 = new P130SurroundedRegions();
        p130.solve(board);
    }
}