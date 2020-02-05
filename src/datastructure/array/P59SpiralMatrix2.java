package datastructure.array;

/**
* Title: 59. 打印矩阵2(和54题类似)
* Desc: 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
* Created by Myth-PC on 05/02/2020 in VSCode
*/
public class P59SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] array = new int[n][n];
        int left = 0, right = n-1, top = 0, bottom = n-1;
        n = n * n;
        int i, j, num = 1;
        // 思考此处为什么不用像54题那样，在循环内部进行边界判断？？
        // 因为本题永远都是正方形矩阵，四个边界都是对称的，不会出现54那样不对称的情况（例如54题，3*4的矩阵不进行边界判断就会出问题）
        while (num <= n) {
            for (j = left; j <= right; j++) array[top][j] = num++;
            for (i = ++top; i <= bottom; i++) array[i][right] = num++;
            for (j = --right; j >= left; j--) array[bottom][j] = num++;
            for (i = --bottom; i >= top; i--) array[i][left] = num++;
            left++;
        }
        return array;
    }
}