package bisearch.gfunction;

/**
 * Title: 1011. 在 D 天内送达包裹的能力
 * Desc: https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 *      1 <= D <= weights.length <= 50000
 *      1 <= weights[i] <= 500
 *
 * Created by Myth on 8/26/2019
 */
public class P1011CapacityToShipPackages {
    // 最低运载能力 max(weights) <= c <= sum(weights)
    // 那么在这个区间使用二分查找寻找 第一个满足要求的
    public int shipWithinDays(int[] weights, int D) {
        int l = weights[0], r = 0;
        for (int i = 0; i < weights.length; i++) {
            r += weights[i];
            if (l < weights[i]) l = weights[i];
        }
        int mid;
        while (l < r) {
            mid = l + (r - l) / 2;
            if (canSplit(weights, D, mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    private boolean canSplit(int[] weights, int D, int capacity) {
        int count = 0;
        int temp = 0, i = 0;
        while (i < weights.length) {
            temp += weights[i];
            if (temp > capacity) {
                // System.out.println(temp - weights[i] + "--" + (i-1));
                temp = 0;
                i--;
                count++;
            }
            i++;
        }
        if (temp != 0) {
            // System.out.println(temp);
            count++;
        }
        // System.out.println(count);
        return count <= D;
    }

    public static void main(String[] args) {
        P1011CapacityToShipPackages p1011 = new P1011CapacityToShipPackages();
        int[] weights1 = {1,2,3,4,5,6,7,8,9,10};
        int[] weights2 = {3,2,2,4,1,4};
        System.out.println(p1011.shipWithinDays(weights1, 5));
        System.out.println(p1011.shipWithinDays(weights2, 3));
    }
}
