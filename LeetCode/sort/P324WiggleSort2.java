package sort;

import java.util.Arrays;

/**
 * Title: 324. 摆动排序
 * Desc: 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * (隐藏条件：输入序列在排序后最多只有序列长度一半(n/2)的相邻的数连续相等。)
 * Created by Myth-Lab on 10/18/2019
 */
public class P324WiggleSort2 {
    // 先排序, 分成两部分
    // 穿插放到一个数组里面
    // TestCase : [4,5,5,6]
    public void wiggleSort(int[] nums) {
        // nums长度肯定符合要求
        int n = nums.length;
        Arrays.sort(nums);
        // [0,mid) [mid, n)
        int mid = (n%2==0) ? n/2 : n/2+1;
        int[] copyNums = Arrays.copyOf(nums, n);
        int odd = 0;
        for (int i = mid-1; i >= 0; i--) {
            nums[odd] = copyNums[i];
            odd += 2;
        }
        int even = 1;
        for (int i = n-1; i >= mid; i--) {
            nums[even] = copyNums[i];
            even += 2;
        }
    }
}
