package dp.seq;

/**
 * Title: 303. 区域和检索 - 数组不可变
 * Desc:  会多次调用 sumRange 方法。
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
示例：
给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

 * Created by Myth-Lab on 11/20/2019
 */
public class P303RangeSumQueryImmutable {
    // 会多次调用sumRange，所以进行一个记忆化存储
    class NumArray {
        int[] numsSum;
        public NumArray(int[] nums) {
            numsSum = new int[nums.length];
            if (nums.length == 0) return;
            int sum = 0;
            numsSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                numsSum[i] = numsSum[i-1] + nums[i];
            }
        }
        // range(i,j) = range(j)-range(i-1)
        public int sumRange(int i, int j) {
            if (i == 0) return numsSum[j];
            return numsSum[j]-numsSum[i-1];
        }
    }

}
