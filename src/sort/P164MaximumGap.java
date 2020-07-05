package sort;

import java.util.Arrays;

/**
 * Title: 164. 最大间距
 * Desc: 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。如果数组元素个数小于 2，则返回 0。
 * 直观解法很简单，O(NlogN+N)
 * Created by Myth-Lab on 10/16/2019
 */
public class P164MaximumGap {
    // 桶排序
    // 将n个元素放进n + 1个桶里，至少会有一个空桶。同一个桶内的差值必然小于capacity，而间隔一个空桶的差值必然会大于capacity。
    // 因此，最大差值只可能在空桶的两边产生，即空桶的后一个非空桶的最小值减去空桶的前一个非空桶的最大值。
    // n-1个桶，那么桶容量bucket = (max-min)/(n-1)  向下取整，当为0的时候，置为1
    // 每个元素在在第  (num-min)/bucket 取下界 个桶（下标从0开始）
    // 难点就是 桶的大小和数目
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int value : nums) {
            if (value < min) min = value;
            if (value > max) max = value;
        }
        // 注意size有可能为0
        int size = Math.max(1, (max - min) / (n - 1));  // 桶是左闭右开[ )
        int bucketNum = (max - min) / size + 1;
        // 保存每个桶的最大值和最小值 int[i][0]最小值，int[i][1]最大值
        int[][] minMax = new int[bucketNum][2];
        for (int i = 0; i < bucketNum; i++) {
            minMax[i][0] = -1;
            minMax[i][1] = -1;
        }
        for (int num : nums) {
            int index = (num - min) / size;
            if (minMax[index][0] == -1 || minMax[index][0] > num) minMax[index][0] = num;
            if (minMax[index][1] == -1 || minMax[index][1] < num) minMax[index][1] = num;
        }
        int pre = minMax[0][1];
        int maxGap = 0;
        for (int i = 1; i < bucketNum; i++) {
            if (minMax[i][0] != -1) {
                maxGap = Math.max(maxGap, minMax[i][0]-pre);
                pre = minMax[i][1];
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        P164MaximumGap p164 = new P164MaximumGap();
        int[] nums = {1,1,1,1,1,5,5,5,5,5};
        System.out.println(p164.maximumGap(nums));
    }
}
