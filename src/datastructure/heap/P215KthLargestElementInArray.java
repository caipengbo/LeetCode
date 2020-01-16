package datastructure.heap;

import java.util.PriorityQueue;

/**
* Title: 215. Kth Largest Element in an Array
* Desc: 
* Created by Myth on 01/16/2020 in VSCode
*/

public class P215KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
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