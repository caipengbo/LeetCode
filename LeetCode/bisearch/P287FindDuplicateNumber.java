package bisearch;

/**
 * Title: 287. 寻找重复数
 * Desc: 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 * 说明：
 *      不能更改原数组（假设数组是只读的）。
 *      只能使用额外的 O(1) 的空间。
 *      时间复杂度小于 O(n2) 。
 *      数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * Created by Myth on 8/28/2019
 */
public class P287FindDuplicateNumber {
    // 从1 - n 二分缩短区间，然后统计数组中小于mid的数目，如果大于mid，说明[1,mid]有重复
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length;
        int mid, count;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) count++;
            }
            if (count > mid) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        P287FindDuplicateNumber p287 = new P287FindDuplicateNumber();
        int[] nums1 = {1,3,4,2,2};
        int[] nums2 = {3,1,3,4,2};
        System.out.println(p287.findDuplicate(nums1));
        System.out.println(p287.findDuplicate(nums2));
    }
}
