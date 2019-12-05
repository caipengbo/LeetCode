package sort;

import java.util.*;

/**
 * Title: 57. 插入区间
 * Desc: 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 在第56题基础上
 * Created by Myth-Lab on 10/16/2019
 */
public class P57InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) return new int[][]{newInterval};
        List<int[]> ret = new LinkedList<>();
        // 右边比新区间起始断点小的，直接加入结果
        int i = 0;
        for (i = 0; i < intervals.length; i++) {
            if (intervals[i][1] >= newInterval[0]) break;
            ret.add(new int[]{intervals[i][0], intervals[i][1]});
        }
        int left, right;
        left = (i == intervals.length) ? newInterval[0]  : Math.min(intervals[i][0], newInterval[0]);
        right = newInterval[1];
        // 找插入位置，并且合并重叠区间
        int j = i;
        for (j = i; j < intervals.length; j++) {
            // 小于左区间
            if (right < intervals[j][0]) {
                break;
            } else if (right < intervals[j][1]) {  // 小于右区间
                right = intervals[j][1];
                j++;
                break;
            }
        }
        ret.add(new int[]{left, right});
        // 将剩余的区间加入结果
        for (int k = j; k < intervals.length; k++) {
            ret.add(new int[]{intervals[k][0], intervals[k][1]});
        }

        return ret.toArray(new int[0][]);
    }
}
