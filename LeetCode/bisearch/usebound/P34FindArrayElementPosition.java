package bisearch.usebound;

import java.util.Arrays;

/**
 * Title: 34. 在排序数组中查找元素的第一个和最后一个位置
 * Desc: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * Created by Myth on 8/1/2019
 */
public class P34FindArrayElementPosition {
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[]{-1,-1};
        if (nums == null || nums.length == 0) return ret;
        int l = 0, r = nums.length - 1;
        int m;
        // 找最左边的位置
        while (l < r) {
            m = l + (r -l) / 2;
            if (nums[m] >= target) r = m;
            else l = m + 1;
        }
        if (nums[l] == target) ret[0] = l;
        // 找右边的位置
        l = 0;
        r = nums.length - 1;
        while (l < r) {
            m = l + (r -l) / 2;
            if (nums[m] > target) r = m;
            else l = m + 1;
        }
        if ((l == 0 || l == nums.length - 1) && nums[l] == target) ret[1] = l;
        else if (l > 0 && nums[l-1] == target) ret[1] = l-1;
        return ret;
    }

    public static void main(String[] args) {
        P34FindArrayElementPosition p34 = new P34FindArrayElementPosition();
        int[] nums = {5,7,7,8,8,10};
        int target1 = 8;
        int target2 = 6;
        int target3 = 7;
        int target4 = 10;
        int target5 = 11;
        int[] nums2 = {1};
        int[] nums3 = {1, 1};
//        System.out.println(Arrays.toString(p34.searchRange(nums, 4)));
//        System.out.println(Arrays.toString(p34.searchRange(nums, 5)));
//        System.out.println(Arrays.toString(p34.searchRange(nums, 6)));
//        System.out.println(Arrays.toString(p34.searchRange(nums, 7)));
//        System.out.println(Arrays.toString(p34.searchRange(nums, 8)));
//        System.out.println(Arrays.toString(p34.searchRange(nums, 9)));
        System.out.println(Arrays.toString(p34.searchRange(nums, 10)));
        System.out.println(Arrays.toString(p34.searchRange(nums2, 1)));
        System.out.println(Arrays.toString(p34.searchRange(nums2, 5)));
        System.out.println(Arrays.toString(p34.searchRange(nums2, 5)));
        System.out.println(Arrays.toString(p34.searchRange(nums3, 1)));
        System.out.println(Arrays.toString(p34.searchRange(nums3, 2)));
    }
}
