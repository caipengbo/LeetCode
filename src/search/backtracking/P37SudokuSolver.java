package search.backtracking;

/**
 * Title: 37. 解数独
 * Desc: 解答 9X9 的数独 的一个解
 * Created by Myth on 7/25/2019
 */
public class P37SudokuSolver {
    // 注意
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return;
        backtracking(board, 0, 0);
    }
    // row, col  当前位置
    private boolean backtracking(char[][] board, int row, int col) {
        for (int i = row; i < 9; i++) {
            for (int j = col; j < 9; j++) {
                if (board[i][j] != '.') continue;
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(board, i, j, c)) {
                        board[i][j] = c;
                        if (backtracking(board, i, j+1)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
            col = 0; // 注意此处
        }
        return true;
    }
    // 在(row, col) 位置 放置 c,是否有效
    private boolean isValid(char[][] board, int row, int col, char c) {
        int x, y; // 3X3 index
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.'&& board[row][i] == c) return false;  // 行
            if (board[i][col] != '.' && board[i][col] == c) return false; // 列
            // 3X3 格 难点！！！
            x = 3 * (row / 3) + i / 3;
            y = 3 * (col / 3) + i % 3;
            if (board[x][y] != '.' && board[x][y] == c) return false;
        }
        return true;
    }
    private void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        P37SudokuSolver p37 = new P37SudokuSolver();
        p37.solveSudoku(board);
        p37.printBoard(board);
    }
}
