package bisearch.usebound;

/**
 * Title: 4. 寻找两个有序数组的中位数
 * Desc: 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * Created by Myth on 9/3/2019
 */
public class P4MedianTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l, r;
        int k1 = 0, k2 = 0;
        int median1 = 0, median2 = 0;
        if (m == 0) {
            l = nums2[0];
            r = nums2[n-1];
        } else if (n == 0) {
            l = nums1[0];
            r = nums1[m-1];
        } else {
            l = Math.min(nums1[0], nums2[0]);
            r = Math.max(nums1[m-1], nums2[n-1]);
        }
        if ((m + n) % 2 == 0) { // 偶数
            median1 = findKSmallestInTwoArray(nums1, nums2, l, r, (m + n) / 2);
            median2 = findKSmallestInTwoArray(nums1, nums2, l, r, (m + n) / 2 + 1);
            return (double)(median1+median2)/2;
        } else {
            median1 = findKSmallestInTwoArray(nums1, nums2, l, r, (m + n + 1) / 2);
            return (double) median1;
        }
    }
    public int findKSmallestInTwoArray(int[] nums1, int[] nums2, int l, int r, int k) {
        if (k == 0) return 0;
        int count1, count2;
        int mid;
        while (l < r) {
            mid = l + (r - l) / 2;
            count1 = findKSmallest(nums1, mid);
            count2 = findKSmallest(nums2, mid);
            if (count1 + count2 >= k) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    // <= val 数的数目（注意val不一定在数组中哦）
    public int findKSmallest(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int l = 0, r = nums.length-1, m;
        while (l < r) {
            m = l + (r - l) / 2;
            if(nums[m] > val) r = m;
            else l = m + 1;
        }
        if (nums[l] > val) return l;
        return l+1;
    }

    public static void main(String[] args) {
        P4MedianTwoSortedArrays p4 = new P4MedianTwoSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        int[] nums5 = {1, 1};
        int[] nums6 = {1, 2, 2, 2, 5};
//        System.out.println(p4.findMedianSortedArrays(nums1, nums2));
//        System.out.println(p4.findMedianSortedArrays(nums3, nums4));
//        System.out.println(p4.findMedianSortedArrays(nums1, nums4));
//        System.out.println(p4.findMedianSortedArrays(nums2, nums4));
//        System.out.println(p4.findMedianSortedArrays(nums5, nums3));
        System.out.println(p4.findKSmallest(nums6, 0));
        System.out.println(p4.findKSmallest(nums6, 1));
        System.out.println(p4.findKSmallest(nums6, 2));
        System.out.println(p4.findKSmallest(nums6, 5));
        System.out.println(p4.findKSmallest(nums6, 6));
    }
}
