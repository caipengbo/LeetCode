package bisearch;

import java.util.Arrays;

/**
 * Title: 719. 找出第 k 小的距离对
 * Desc: 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * 说明：
 *      其中如何统计距离对，就涉及到如何 设计 g(x) 函数
 * Created by Myth on 8/26/2019
 */
public class P719FindKthSmallestPairDistance {
    // 思路： 距离对肯定 小于 maxDis = max(nums) - min(nums), 所以在 [0, maxDis]内进行 二分查找
    // 对于当前距离 cur, 统计 <= countPairs(cur)的数目, 等于k时满足条件
    // 重点就是：如何统计距离对
    public int smallestDistancePair(int[] nums, int k) {
        // if (nums == null || nums.length == 0 || k <= 0) return 0;
        Arrays.sort(nums);
        int l = 0, r = nums[nums.length-1] - nums[0] + 1;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            if (countPairs2(nums, m) >= k) r = m;
            else l = m + 1;
        }
        return l;
    }
    // 双指针寻找  <= mid 的距离对 数
    //我们维护 left 和 right，其中 right 通过循环逐渐递增，left 在每次循环中被维护，
    // 使得它满足 nums[right] - nums[left] <= mid 且最小。这样对于 nums[right]，
    // 以它为右端的满足距离小于等于 mid 的距离对数目即为 right - left
    // 通俗来讲就是模拟了 枚举的过程
    private int countPairs(int[] nums, int mid) {
        int i, j, count = 0;
        for (i = 0; i + 1 < nums.length; i++) {
            j = i + 1;
            while (j < nums.length && nums[j] - nums[i] <= mid) j++;
            count += j - i - 1;
        }
        return count;
    }
    // 在上面的countPairs基础之上改进，使用二分查找找到 j
    private int countPairs2(int[] nums, int mid) {
        int i, j, count = 0;
        for (i = 0; i + 1 < nums.length; i++) {
            j = i + 1;
            count += upperBound(nums, i, nums.length, nums[i] + mid)- i - 1;
        }
        return count;
    }
    // 找 j
    private int upperBound(int[] nums, int start, int end, int key) {
        if (key >= nums[end-1]) return end;
        int mid;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] > key) end = mid;
            else start = mid + 1;
        }
        return start;
    }

    public static void main(String[] args) {
        P719FindKthSmallestPairDistance p719 = new P719FindKthSmallestPairDistance();
        int[] nums1 = {1, 3, 1};
        System.out.println(p719.smallestDistancePair(nums1, 2));
    }
}
