package twopointer;

import util.IOUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Title: 239. 滑动窗口最大值
 * Desc: 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * Created by Myth-Lab on 10/15/2019
 */
public class P239SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || k > nums.length) return new int[0];
        int[] ret = new int[nums.length-k+1];
        int p = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            if (deque.isEmpty()) deque.add(i);
            else {
                while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) deque.removeLast();
                deque.add(i);
            }
            if (i - deque.getFirst() >= k) deque.removeFirst();
            if (i >= k-1 && !deque.isEmpty()) ret[p++] = nums[deque.getFirst()];
        }
        return ret;
    }
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (k == 0 || k > nums.length) return new int[0];
        int[] ret = new int[nums.length-k+1];
        int p = 0;
        if (nums.length >= k && k >= 1) {
            Deque<Integer> deque = new ArrayDeque<>();
            // 窗口未满的时候
            for (int i = 0; i < k; i++) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()]) {
                    deque.removeLast();
                }
                deque.addLast(i);
            }

            for (int i = k; i < nums.length; i++) {
                ret[p++] = nums[deque.getFirst()];
                while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()]) {
                    deque.removeLast();
                }
                if (!deque.isEmpty() && deque.getFirst() <= (i-k)) {
                    deque.removeFirst();
                }
                deque.addLast(i);
            }
            ret[p++] = nums[deque.getFirst()];
        }
        return ret;
    }

    public static void main(String[] args) {
        P239SlidingWindowMaximum p239 = new P239SlidingWindowMaximum();

    }
}
