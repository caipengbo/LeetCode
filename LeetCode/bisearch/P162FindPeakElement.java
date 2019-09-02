package bisearch;

/**
 * Title: 162. 寻找峰值（找局部最大值和852题一模一样）
 * Desc:  峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * Created by Myth on 9/1/2019
 */
public class P162FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int l = 0, r = nums.length - 1;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            if (nums[m] > nums[m+1]) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        P162FindPeakElement p162 = new P162FindPeakElement();
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(p162.findPeakElement(nums1));
        System.out.println(p162.findPeakElement(nums2));
    }
}
