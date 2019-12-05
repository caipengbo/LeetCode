package sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 969. 煎饼排序
 * Desc: 给定数组 A，我们可以对其进行煎饼翻转：我们选择一些正整数 k <= A.length，然后反转 A 的前 k 个元素的顺序。
 * 我们要执行零次或多次煎饼翻转（按顺序一次接一次地进行）以完成对数组 A 的排序。
 * 返回能使 A 排序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * A.length 范围内的有效答案都将被判断为正确。
 * Created by Myth-Lab on 10/20/2019
 */
public class P969PancakeSorting {
    // 有点分治的意思
    // 每次取最大的，然后交换到首部，再倒转到尾部
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ret = new LinkedList<>();
        int maxPos = 0, curLen = A.length;
        boolean sorted = false;
        while (curLen > 0) {
            //  找到当前未排序序列的（设置flag查看是否已经排序） 最大的位置 maxPos ，翻转maxPos+1
            sorted = true;
            maxPos = 0;
            for (int i = 0; i < curLen; i++) {
                if (A[maxPos] < A[i]) maxPos = i;
                if (i+1 < curLen && A[i] > A[i+1]) sorted = false;
            }
            if (sorted) return ret;
            // 如果最大值不是当前未排序的最后一个元素就需要交换
            if (maxPos+1 != curLen) {
                if (maxPos != 0) {
                    ret.add(maxPos+1);
                    reverse(A, maxPos+1);
                }
                // 翻转当前未排序的长度curLen
                ret.add(curLen);
                reverse(A, curLen);
            }
            // 未排序长度-1
            curLen--;
        }
        return ret;
    }
    private void reverse(int[] A, int len) {
        for (int i = 0; i < len/2; i++) {
            int temp = A[i];
            A[i] = A[len-1-i];
            A[len-1-i] = temp;
        }
    }

    public static void main(String[] args) {
        P969PancakeSorting p969 = new P969PancakeSorting();
        int[] A = {2, 1, 3};
        System.out.println(p969.pancakeSort(A));
    }
}
