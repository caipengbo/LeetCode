package bisearch.rotated;

/**
 * Title: 153. 寻找旋转排序数组中的最小值 II
 * Desc: 153题的拓展问题
 * Created by Myth on 8/30/2019
 */
public class P154MinimumInRotatedSortedArray2 {
    // 旋转点就是最小值
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[hi]) hi = mid;
            else if (nums[mid] > nums[hi]) lo = mid + 1;
            else hi--;
        }
        return nums[lo];  // 得到的是 分界点 p, 左侧是有序的(0, p-1)有序, 右侧(p, len-1)也是有序的
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {1,1,0,1};
        int[] nums3 = {1,0,1,1,1};
        int[] nums4 = {1,1,1,1};
        P154MinimumInRotatedSortedArray2 p154 = new P154MinimumInRotatedSortedArray2();
        System.out.println(p154.findMin(nums1));
        System.out.println(p154.findMin(nums2));
        System.out.println(p154.findMin(nums3));
        System.out.println(p154.findMin(nums4));
    }
}
