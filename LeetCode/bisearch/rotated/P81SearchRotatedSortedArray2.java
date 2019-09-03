package bisearch.rotated;

/**
 * Title: 81. 搜索旋转排序数组 II
 * Desc: 这是 33.搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。
 * Created by Myth on 8/31/2019
 */
public class P81SearchRotatedSortedArray2 {
    //  [1,3,1,1,1]   [1,1,1,3,1]
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) return false;
        int left = 0, right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            // if (target == nums[mid]) return true;
            // 和33题不同的地方
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) { // 左边有序（旋转点在右边）
                if (nums[left] <= target && target <= nums[mid]) right = mid;
                else left = mid + 1;
            } else { // 右边有序（旋转点在左边）
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid;
            }
        }
        return nums[left] == target;
    }

    public static void main(String[] args) {
        P81SearchRotatedSortedArray2 p81 = new P81SearchRotatedSortedArray2();
        int[] nums1 = {2,5,6,0,0,1,2};
        int[] nums2 = {1,1,1,3,1};
        int[] nums3 = {0,1,1};
        int[] nums4 = {1,3,1,1,1};
//        System.out.println(p81.search(nums1, 2));
//        System.out.println(p81.search(nums1, 6));
//        System.out.println(p81.search(nums1, 0));
//        System.out.println(p81.search(nums1, 3));
//        System.out.println(p81.search(nums1, 8));
//        System.out.println(p81.search(nums2, 1));
//        System.out.println(p81.search(nums2, 3));
//        System.out.println(p81.search(nums2, 2));
//        System.out.println(p81.search(nums2, 8));
        int[] nums5 = {1, 3};
        System.out.println(p81.search(nums5, 1));
        System.out.println(p81.search(nums5, 3));
        System.out.println(p81.search(nums5, 8));
    }
}
