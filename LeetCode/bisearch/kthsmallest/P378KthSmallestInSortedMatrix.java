package bisearch.kthsmallest;

/**
 * Title: 378. 有序矩阵中第K小的元素
 * Desc: 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n^2
 * 这种并不是 一个整个的排序list, 而是一个二维的，需要用到 范围搜索+索引搜索
 * 和 P719 基本一样
 *
 * Created by Myth on 8/27/2019
 */
public class P378KthSmallestInSortedMatrix {
    // 范围搜索 + 索引搜索
    // 二维数组第一个元素是最小值，最后一个元素是最大值，在这两个数之间使用二分法寻找 第K小元素
    // min(matrix) <= candidate <= max(matrix),  然后再次使用二分法统计 每一行比candidate小的元素数目，来判断candidate是否符合要求
    // 范围搜索
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length-1][matrix.length-1] + 1;
        int mid, count;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            count = 0;
            for (int i = 0; i < matrix.length; i++) {
                count += countRow(matrix[i], mid);
            }
            if (count >= k) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
    // 二分法：每一行 <= target 的数目，也就是 下边界
    // 索引搜索
    public int countRow(int[] row, int target) {
        int lo = 0, hi = row.length;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (row[mid] > target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        P378KthSmallestInSortedMatrix p378 = new P378KthSmallestInSortedMatrix();
        int[][] matrix1 = {{1,  5,  9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println(p378.kthSmallest(matrix1, -1));
    }
}
