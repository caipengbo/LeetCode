package datastructure.array;

/**
* Title: 289. 生命游戏
* Desc: 纪念因为2020年新冠状病毒去世的英国数学家 约翰·何顿·康威
给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。
每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
    如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
    如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
    如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
    如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。
下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
* Created by Myth-MBP on 18/04/2020 in VSCode
*/

public class P289LifeGame {
    // 难点：1. 同时更新，不能使用本次更新的结果 2. 边界如何处理？
    // 方案：1. 复制一份当前数组   2. 保存之前的状态
    // 方法2： 如何保存之前的状态？ 
    // 1. 使用 2 3 4 5 数字代表状态
    // 2. 使用 int的 倒数第二bit代表之前的状态
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;
        // 使用倒数第二个bit位，记录之前的状态
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) board[i][j] = (board[i][j] | 2);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                changeState(board, m, n, i, j);
            }
        }
        // 保留最低位
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (board[i][j] & 1);
            }
        }

    }
    private void changeState(int[][] board, int m, int n, int i, int j) {
        int count = 0;
        if (i > 0) {
            if (j > 0 && (board[i-1][j-1]&2) != 0) count++;
            if ((board[i-1][j]&2) != 0) count++;
            if (j < n-1 && (board[i-1][j+1]&2) != 0) count++;
        }
        if (j > 0 && (board[i][j-1]&2) != 0) count++;
        if (j < n-1 && (board[i][j+1]&2) != 0) count++;
        
        if (i < m-1) {
            if (j > 0 && (board[i+1][j-1]&2) != 0) count++;
            if ((board[i+1][j]&2) != 0) count++;
            if (j < n-1 && (board[i+1][j+1]&2) != 0) count++;
        }
        if ((board[i][j]&2) != 0 && (count < 2 || count > 3)) board[i][j] = (board[i][j]&2);
        else if ((board[i][j]&2) == 0 && (count == 3)) board[i][j] = (board[i][j]|1);
    }
}