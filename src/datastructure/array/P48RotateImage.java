package datastructure.array;


/**
* Title: 48. 旋转图像
* Desc: 给定一个 n × n 的二维矩阵表示一个图像。原地 将图像顺时针旋转 90 度。
* Created by Myth-MBP on 13/05/2020 in VSCode
*/

public class P48RotateImage {
    // 使用两次旋转
    // 1. 右上角 左下角旋转    2. 然后左右旋转
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 右上角 左下角旋转 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        // 左右旋转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                swap(matrix, i, j, i, n-1-j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
    private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{4,5,6,7}, {7,8,9,10}, {11,12,13,14}};
        P48RotateImage p48 = new P48RotateImage();
        p48.rotate(matrix);
        StringBuilder sb = new StringBuilder();
        sb.delete(, end)
    }

}