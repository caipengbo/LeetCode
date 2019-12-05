package bisearch.usebound;

/**
 * Title: 704. 二分查找
 * Desc: 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * Created by Myth on 8/31/2019
 */
public class P704BinarySearch {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        int m;
        while(l < r) {
            m = l + (r - l) / 2;
            if(nums[m] >= target) r = m;
            else l = m + 1;
        }
        if(nums[l] == target) return l;
        return -1;
    }

    public static void main(String[] args) {
        P704BinarySearch p704 = new P704BinarySearch();

        int[] nums1 = {-1,0,3,5,9,12};
        int[] nums2 = {-1,0,3,5,9,12,14};
        int[] nums3 = {-1};
        int[] nums4 = {-1, 2};
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(p704.search(nums1, nums1[i]));
        }
        System.out.println(p704.search(nums1, -8));
        System.out.println(p704.search(nums1, 15));
        for (int i = 0; i < nums2.length; i++) {
            System.out.println(p704.search(nums2, nums2[i]));
        }
        System.out.println(p704.search(nums2, -8));
        System.out.println(p704.search(nums2, 15));
        System.out.println("=======");
        System.out.println(p704.search(nums3, -1));
        System.out.println(p704.search(nums3, -4));
        System.out.println(p704.search(nums3, 4));
        System.out.println(p704.search(nums4, -1));
        System.out.println(p704.search(nums4, 2));
        System.out.println(p704.search(nums4, -4));
        System.out.println(p704.search(nums4, 4));
    }
}
