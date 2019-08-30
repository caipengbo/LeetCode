package bisearch;

/**
 * Title: 153. 寻找旋转排序数组中的最小值
 * Desc: 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。你可以假设数组中不存在重复元素。
 * Created by Myth on 8/30/2019
 */
public class P153MinimumInRotatedSortedArray {
    // 旋转点就是最小值
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[hi]) hi = mid;
            else lo = mid + 1;
        }
        return nums[lo];  // 得到的是 分界点 p, 左侧是有序的(0, p-1)有序, 右侧(p, len-1)也是有序的
    }
}
