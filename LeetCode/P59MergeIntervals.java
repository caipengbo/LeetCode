import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 56. 合并区间
 * Desc: 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * Created by Myth-PC on 2019-09-25
 */
public class P59MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> ret = new LinkedList<>();
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
        return (int[][]) ret.toArray();
    }
}
