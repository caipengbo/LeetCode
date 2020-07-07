package datastructure.heap;

import java.util.PriorityQueue;

/**
* Title: 215. 数组中的第K个最大元素
* Desc: 本解法使用分治，使用堆；使用快排的partition函数的解法见 divideconquer包，
* Created by Myth on 01/16/2020 in VSCode
*/

public class P215KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        // 小顶堆：会收集最大的K个数，然后堆顶是最小的（即最大的K个数里面最小的，第K大的）
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, (o1, o2)->o1.compareTo(o2));
        for (int i = 0; i < nums.length; i++) {
            if (i < k) heap.add(nums[i]);
            else if (nums[i] > heap.peek()) {
                heap.poll();
                heap.add(nums[i]);
            }
        }

        while (!heap.isEmpty()) {
            System.out.print(heap.poll() + " ");
        }
        // return heap.peek();
        return 0;
    }
    public static void main(String[] args) {
        P215KthLargestElementInArray p215 = new P215KthLargestElementInArray();
        int[] nums = {3,2,1,5,6,4};
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        p215.findKthLargest(nums2, 4);
    }
}