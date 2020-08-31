package bisearch.twodim;

/**
 * Title: 74. 搜索二维矩阵   （双指针包内240题 矩阵每行每列都是升序）
 * Desc: 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *      - 每行中的整数从左到右按升序排列。
 *      - 每行的第一个整数大于前一行的最后一个整数。
 *
 * Created by Myth on 8/25/2019
 */
public class P74Search2DMatrix {
    // 难点： 如何将 2d -> 1d : 得到 位置的数字 然后转换成 坐标就可以了
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n;
        int mid, i, j;
        while (l < r) {
            mid = l + (r - l) / 2;
            i = mid / n;
            j = mid % n;
            if (matrix[i][j] < target) l = mid + 1;
            else r = mid;
        }
        if (l >= m * n) return false;
        i = l / n;
        j = l % n;
        return  matrix[i][j] == target;
    }

    public static void main(String[] args) {
        P74Search2DMatrix p74 = new P74Search2DMatrix();
        int[][] mat1 = {{1}};
        int[][] mat2 = {{}};
        int[][] mat3 = {{1}, {2}, {3}, {4}};
        int[][] mat4 = {{1, 2, 3, 4, 5}};
        int[][] mat5 = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        // System.out.println(p74.searchMatrix(mat4, 3));
        System.out.println(p74.searchMatrix(mat5, 35));
    }
}
