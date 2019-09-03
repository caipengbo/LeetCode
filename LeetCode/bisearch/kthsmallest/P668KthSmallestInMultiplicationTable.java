package bisearch.kthsmallest;

/**
 * Title: 668. 乘法表中第k小的数
 * Desc: https://leetcode-cn.com/problems/kth-smallest-number-in-multiplication-table/
 *
 * 和 P378 一样
 * Created by Myth on 8/28/2019
 */
public class P668KthSmallestInMultiplicationTable {
    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m * n + 1;
        int mid, count;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            count = 0;
            for (int i = 1; i <= m; i++) {
                count += (mid/i > n ? n : mid/i);
            }
            if (count >= k) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

}
