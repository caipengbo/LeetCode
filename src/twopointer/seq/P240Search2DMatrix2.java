package twopointer.seq;

/**
* Title: 240. 搜索二维矩阵 II
* Desc: 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
该矩阵具有以下特性：
    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。
* Created by Myth-PC on 26/01/2020 in VSCode
*/
public class P240Search2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length, row = 0, col = n-1;
        while (row < m && col >= 0) {
            if (matrix[row][col] < target) row++;
            else if (matrix[row][col] > target) col--;
            else return true;
        }
        return false;
    }
}