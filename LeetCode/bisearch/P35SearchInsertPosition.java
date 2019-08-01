package bisearch;

/**
 * Title: 35. 搜索插入位置
 * Desc: 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * Created by Myth on 8/1/2019
 */
// 典型的二分查找
public class P35SearchInsertPosition {
    // 找到
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            // 注意此处为何不是 <= ?
            if (nums[m] < target) l = m + 1; // 右侧
            else r = m;  // 左侧
        }
        return l;
    }

    public static void main(String[] args) {
        P35SearchInsertPosition p35 = new P35SearchInsertPosition();
        int[] nums = {1,3,5,6};
        int target1 = 5;
        int target2 = 2;
        int target3 = 7;
        int target4 = 0;
        System.out.println(p35.searchInsert(nums, target1));
        System.out.println(p35.searchInsert(nums, target2));
        System.out.println(p35.searchInsert(nums, target3));
        System.out.println(p35.searchInsert(nums, target4));
    }
}
