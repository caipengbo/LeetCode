package twopointer;

/**
 * Title: 26. 删除排序数组的重复项
 * Desc: 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。不需要考虑数组中超出新长度后面的元素。
 * Created by Myth-PC on 2019-10-07
 */
public class P26RemoveDuplicatesfromSortedArray {
    // 双指针，一个指针i指向非重复部分的尾部，一个指针遍历数组
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 0;
        while (j < nums.length) {
            while (j+1 < nums.length && nums[j] == nums[j+1]) j++;
            nums[i++] = nums[j++];
        }
        return i;
    }
    public int removeDuplicates2(int[] nums) {
        int i = 0, len = nums.length;
        for (int j = 0; j < len; j++) {
            if (j+1 < len && nums[j] != nums[j+1]) nums[i++] = nums[j];
        }
        if (len > 0) nums[i++] = nums[len-1];
        return i;
    }
}
