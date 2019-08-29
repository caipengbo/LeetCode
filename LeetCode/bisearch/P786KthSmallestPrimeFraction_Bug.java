package bisearch;

import java.util.Arrays;

/**
 * Title: 786. 第 K 个最小的素数分数
 * Desc:  一个已排序好的表 A，其包含 1 和其他一些素数.  当列表中的每一个 p<q 时，我们可以构造一个分数 p/q 。
 *
 * 那么第 k 个最小的分数是多少呢?  以整数数组的形式返回你的答案, 这里 answer[0] = p 且 answer[1] = q.
 * 注意：排序顺序并不是有规律的
 *
 * 说明：
 *      - A 的取值范围在 2 — 2000.
 *      - 每个 A[i] 的值在 1 —30000.
 *      - K 取值范围为 1 —A.length * (A.length - 1) / 2
 * Created by Myth on 8/29/2019
 */
public class P786KthSmallestPrimeFraction_Bug {
    // 将此类问题转换成 2D 有序矩阵寻找 K th 问题
    // 如何记录 坐标  p q
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double lo = 0, hi = 1, mid;
        int p = 0, q = 1;
        int i, j;
        int count;
        while (true) {
            mid = (lo + hi) / 2;
            count = 0;
            for (i = 0; i < A.length; i++) {
                j = 0;
                while (j < A.length-1-i && mid > (double) A[i]/A[A.length-1-j]) {
                    j++;
                }
                count += j;
                // 重点：p/q是比 mid小的数的最大值
                if (j >0 && j < A.length  && ((double)p/q) < ((double)A[i]/A[A.length-j])) {
                    p = A[i];
                    q = A[A.length-j];
                    System.out.println("--[" + p + ", " + q + "]--");
                }
            }
            // System.out.println(count);
            if (count > K) hi = mid;
            else if (count < K) lo = mid;
            else return new int[]{p, q};
        }
        // return null;
    }
    public int[] kthSmallestPrimeFraction2(int[] A, int K) {
        double l = 0, r = 1;
        int p = 0, q = 1;

        for (int n = A.length, cnt = 0; true; cnt = 0, p = 0) {
            double m = (l + r) / 2;

            for (int i = 0, j = n - 1; i < n; i++) {
                while (j >= 0 && A[i] > m * A[n - 1 - j]) j--;
                cnt += (j + 1);

                if (j >= 0 && p * A[n - 1 - j] < q * A[i]) {
                    p = A[i];
                    q = A[n - 1 - j];
                    System.out.println("--[" + p + ", " + q + "]--");
                }
            }
            // System.out.println(cnt);
            if (cnt < K) {
                l = m;
            } else if (cnt > K) {
                r = m;
            } else {
                return new int[] {p, q};
            }
        }
    }

    public static void main(String[] args) {
        P786KthSmallestPrimeFraction_Bug p786 = new P786KthSmallestPrimeFraction_Bug();
        int[] arr1 = {1, 2, 3, 5};
        int[] arr2 = {1, 7};
        for (int i = 1; i <= 6; i++) {
            System.out.println(Arrays.toString(p786.kthSmallestPrimeFraction(arr1, i)));
//            System.out.println(Arrays.toString(p786.kthSmallestPrimeFraction2(arr1, i)));
        }

    }
}
