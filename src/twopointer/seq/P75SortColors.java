package twopointer.seq;

/**
 * Title: 75. 颜色分类
 * Desc: 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * ！！！使用一趟扫描
 * Created by Myth-Lab on 10/13/2019
 */
public class P75SortColors {
    private void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length-1;
        int cur = 0;
        while (cur <= p2) {
            if (nums[cur] == 0) swap(nums, cur++, p0++);
            else if (nums[cur] == 2) swap(nums, cur, p2--);
            else cur++;
        }
    }
}
