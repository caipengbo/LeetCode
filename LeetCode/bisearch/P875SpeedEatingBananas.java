package bisearch;

import java.util.Arrays;

/**
 * Title: 875. 在 H 小时内吃掉所有香蕉的最小速度 K
 * Desc:  珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
 * 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 * https://leetcode-cn.com/problems/koko-eating-bananas/
 * Created by Myth on 8/30/2019
 */
public class P875SpeedEatingBananas {
    //
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1, hi = piles[0];
        for (int i = 0; i < piles.length; i++) {
            if (hi < piles[i]) hi = piles[i];
        }
        int mid, count;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            count = 0;
            for (int i = 0; i < piles.length; i++) {
                if (piles[i] % mid == 0) count += piles[i] / mid;
                else count += piles[i] / mid + 1;
            }
            if (count <= H) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

}
