package datastructure.array;


/**
* Title: 73. 矩阵置零（和289题生命游戏思想一样）
* Desc: 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
* Created by Myth-MBP on 07/07/2020 in VSCode
*/
public class P73SetMatrixZeroes {
    // 空间复杂度O(1)
    // 使用第一行和第一列作标记，
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0_flag = false;
        boolean col0_flag = false;
        // 第一行是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                row0_flag = true;
                break;
            }
        }
        // 第一列是否有零
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0_flag = true;
                break;
            }
        }
        // 把第一行第一列作为标志位
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // 当前行、当前列置为0
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 置0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 原本第一行就有0
        if (row0_flag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        // 原本第一列就有0
        if (col0_flag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        } 
    }
}