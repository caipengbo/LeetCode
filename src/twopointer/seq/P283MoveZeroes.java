package twopointer.seq;

/**
 * Title: 283. 移动零
 * Desc: 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * Created by Myth-Lab on 10/15/2019
 */
public class P283MoveZeroes {
    // 我的代码，复杂！！！！
    public void moveZeroes(int[] nums) {
        int p = 0, q = 0;
        while(q < nums.length) {
            while(q < nums.length && nums[q] == 0 ) q++;
            if(p == q) {
                p++;
                q++;
            } else if(q < nums.length) {
                nums[p++] = nums[q];
                nums[q++] = 0;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int zeroPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[zeroPos++] = nums[i];
            }
        }
        for (int i = zeroPos; i < nums.length; i++) {
            if (nums[i] != 0) nums[i] = 0;
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void moveZeroes3(int[] nums) {
        int zeroPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, zeroPos++, i);
            }
        }
    }
}
