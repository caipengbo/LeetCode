package bisearch.rotated;

/**
 * Title: 33. 搜索旋转排序数组(延展题见 81)
 * Desc: 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * Created by Myth on 8/30/2019
 */
public class P33SearchRotatedSortedArray {
    // 找旋转的点
    public int search(int[] nums, int target) {
        // if (nums.length == 0) return -1;
        int left = -1, right = -1;

        int rotatedPoint = findRotatedPoint(nums);
        System.out.println("===" + rotatedPoint);
        // 原始做法：
        // if (rotatedPoint != 0) left = biSearch(nums, 0, rotatedPoint-1, target);
        // right = biSearch(nums, rotatedPoint, nums.length-1, target);

        // 新做法：通过target和nums[0] 作比较，就可以知道，target在哪一部分
        if (rotatedPoint != 0 && target >= nums[0]) left = biSearch(nums, 0, rotatedPoint-1, target);
        else right = biSearch(nums, rotatedPoint, nums.length-1, target);

        if (left != -1) return left;
        return right;
    }
    public int findRotatedPoint(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[hi]) hi = mid;
            else lo = mid + 1;
        }
        return lo;  // 得到的是 分界点 p, 左侧是有序的(0, p-1)有序, 右侧(p, len-1)也是有序的
    }
    public int biSearch(int[] nums, int l, int r, int target) {
        int lo = l, hi = r+1, mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        if (lo <= r && nums[lo] == target) return lo;
        return -1;
    }
    //  第二种解法：√
    public int search2(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            // if (target == nums[mid]) return mid;
            // 左边有序（旋转点在右边）
            if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) right = mid;
                else left = mid + 1;
            } else { // 右边有序（旋转点在左边）
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }

    public static void main(String[] args) {
        P33SearchRotatedSortedArray p33 = new P33SearchRotatedSortedArray();
        int[] nums1 = {4,5,6,7,0,1,2};
        int[] nums2 = {5,6,7,0,1,2,4};
        int[] nums3 = {3,4,5,1,2};
        int[] nums4 = {1,2,3,4,5};
        int[] nums5 = {3,1};
        int[] nums6 = {1,3};
        // System.out.println(p33.search2(nums1, 5));
        // System.out.println(p33.search2(nums2, 4));
        // System.out.println(p33.search2(nums3, 8));
        // System.out.println(p33.search2(nums4, 5));
        // System.out.println(p33.search2(nums5, 3));
        // System.out.println(p33.search2(nums5, 1));
        System.out.println(p33.search2(nums6, 1));
//        System.out.println(p33.findRotatedPoint(nums4));
    }
}
