package datastructure.monotonicqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


/**
 * Title: 239. 滑动窗口最大值(双指针单元也有代码)
 * Desc: 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * Created by Myth-Lab on 10/15/2019
 */
public class P239SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0 || k > n) return new int[0];
        Deque<Integer> deque = new ArrayDeque<>();  // index
        int[] ret = new int[n-k+1];
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && i - deque.peekFirst() >= k) deque.removeFirst();  // 去头
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.removeLast();  // 循环去尾
            deque.addLast(i); 
            if (i >= k-1) ret[i-k+1] = nums[deque.peekFirst()];
        }
        return ret;
    }

    public static void main(String[] args) {
        P239SlidingWindowMaximum p239 = new P239SlidingWindowMaximum();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(p239.maxSlidingWindow(nums, 3)));
    }
}
