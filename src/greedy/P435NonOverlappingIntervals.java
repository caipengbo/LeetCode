package greedy;

import java.util.Arrays;

/**
 * Title: 435. 无重叠区间 
 * Desc: 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。 注意:
 * 可以认为区间的终点总是大于它的起点。 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。 
 * Created by Myth on 9/25/2019
 */
public class P435NonOverlappingIntervals {

    // case1  ---prev------ =====cur====   cur.start >= last.end 不删除，更新 last end为当前 end
    // case2  =======cur========          cur.start < last.end删除，不更新end
    //          ----prev-----
    // case3   ------prev----- 
    //                  =====cur=======
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 按照终点排序
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));
        int end = intervals[0][1];
        int count = 1;
        // 由于我们事先排了序，不难发现所有与 x 相交的区间必然会与 x 的 end 相交；
        // 如果一个区间不想与 x 的 end 相交，它的 start 必须要大于（或等于）x 的 end（x就是上一个区间的end）
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];   // 当前区间是不需要删除的，就更新一下end, 记一下数
                count++;  
            }
        }
        return intervals.length - count;
    }
}
