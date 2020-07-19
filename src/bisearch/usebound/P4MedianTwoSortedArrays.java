package bisearch.usebound;

/**
 * Title: 4. 寻找两个有序数组的中位数
 * Desc: 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * Created by Myth on 9/3/2019
 */
public class P4MedianTwoSortedArrays {

    // 找第K小的数

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;  
    }
    // 找两个数组 第K小的数，每次排除 k/2的元素（找每个数组的k/2，然后找数字小的）
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
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
