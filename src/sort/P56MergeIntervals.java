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
    // 个人再实现(√)
    public int[][] merge3(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int left = intervals[0][0], right = intervals[0][1];
        List<int[]> ret = new LinkedList<>();
        // 当前区间的右边和下一个区间的左边比较，判断是否重叠
        for (int i = 1; i < intervals.length; i++) {
            // 重叠，更新 新区间的右边界 为下一个区间右边界
            if (right >= intervals[i][0]) right = Math.max(right, intervals[i][1]);
            else { // 不重叠，将新区间插入结果集，更新左右边界为下一个区间左右边界
                ret.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        ret.add(new int[]{left, right});
        return ret.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        P56MergeIntervals p59 = new P56MergeIntervals();
        int[][] intervals = {{1,3}, {2,6}, {8, 10}, {15, 18}};
        int[][] ret = p59.merge2(intervals);
        System.out.println(Arrays.toString(ret));
    }
}
