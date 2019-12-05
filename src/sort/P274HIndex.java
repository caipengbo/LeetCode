package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: H 指数
 * Desc: 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 *
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）至多有 h 篇论文分别被引用了至少 h 次。
 * （其余的 N - h 篇论文每篇被引用次数不多于 h 次。）”
 *
 * Created by Myth-Lab on 10/18/2019
 */
public class P274HIndex {
    // 思路 将数组排序，分为两个数组 [0, N-h-1] [N-h,N-1]
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 0) return 0;
        Arrays.sort(citations);
        if (citations[0] > n) return n;
        if (citations[n-1] == 0) return 0;
        if (citations[n-1] == 1) return 1;
        int h;
        for (h = n-1; h > 0; h--) {
            if (n-h-1 >= 0 && citations[n-h-1] <= h && n-h <= n-1 && citations[n-h] >= h) break;
        }
        return h;
    }
    public int hIndex2(int[] citations) {
        int n = citations.length;
        if (n == 0) return 0;
        Arrays.sort(citations);
        int h = 0;
        while (h < n && citations[n-1-h] > h) {
            h++;
        }
        return h;
    }
    public int hIndex3(int[] citations) {
        int n = citations.length;
        List<Integer> list = new LinkedList<>();
        for (int citation : citations) {
            list.add(citation);
        }
        list.sort(Collections.reverseOrder());
        int h = 0;
        while (h < n && list.get(h) > h) {
            h++;
        }
        return h;
    }


    public static void main(String[] args) {
        P274HIndex p274 = new P274HIndex();
        int[] citations1 = {2,0,6,1,5};
        int[] citations2 = {3,0,6,1,5};
        int[] citations3 = {4,0,6,1,5};
        int[] citations4 = {1,2};
        System.out.println(p274.hIndex(citations4));
    }
}
