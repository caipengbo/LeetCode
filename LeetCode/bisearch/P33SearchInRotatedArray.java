package bisearch;

/**
 * Title: 33. 搜索旋转排序数组
 * Desc:
 * Created by Myth-PC on 2019-08-03
 */
public class P33SearchInRotatedArray {
    // 使用二分查找找到最小的元素
    public int search(int[] nums) {
        int l = 0, r = nums.length;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            if (nums[m] < nums[l]) l = m + 1;
            else r = m;
        }
        return nums[l];
    }

    public static void main(String[] args) {
        P33SearchInRotatedArray p33 = new P33SearchInRotatedArray();
        int[] nums1 = {4,5,6,7,0,1,2};
        int[] nums2 = {0,1,2,4,5,6,7};
        int[] nums3 = {1,2,4,5,6,7,0};
        int[] nums4 = {7,0,1,2,4,5,6};
        System.out.println(p33.search(nums1));
        System.out.println(p33.search(nums2));
        System.out.println(p33.search(nums3));
        System.out.println(p33.search(nums4));
    }
}

