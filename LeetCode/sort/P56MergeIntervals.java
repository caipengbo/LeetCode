package sort;

import java.util.*;

/**
 * Title: 56. 合并区间(排序、数组)
 * Desc: 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * Created by Myth-PC on 2019-09-25
 */
public class P56MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> ret = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int[] interval : intervals) {
            if (right >= interval[0]) {
                right = Math.max(right, interval[1]);
            } else {
                ret.add(new int[]{left, right});
                left = interval[0];
                right = interval[1];
            }
        }
        ret.add(new int[]{left, right});
        return ret.toArray(new int[0][]);
    }
    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> ret = new ArrayList<>();
        int[] newInterval = intervals[0];
        for (int[] interval : intervals) {
            if (newInterval[1] >= interval[0]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                ret.add(newInterval);
                newInterval = interval;
            }
        }
        ret.add(newInterval);
        return ret.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        P56MergeIntervals p59 = new P56MergeIntervals();
        int[][] intervals = {{1,3}, {2,6}, {8, 10}, {15, 18}};
        int[][] ret = p59.merge2(intervals);
        System.out.println(Arrays.toString(ret));
    }
}
