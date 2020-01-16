package datastructure.heap;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Title: 优先队列实现堆
 * Desc:
 * Created by Myth-Lab on 10/10/2019
 */
public class PriorityQueueUsage {
    public void useHeap() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, (o1, o2) -> o2.compareTo(o1));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(2);
        int[] arr = {8,2,3,6,5,1,7,9};
        for (int value : arr) {
            minHeap.add(value);
            maxHeap.add(value);
        }
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        while (minHeap.peek() != null) {
            list1.add(minHeap.poll());
        }
        // 遍历的话会遍历存储数组，不是有序的
        for (Integer integer : maxHeap) {
            list2.add(integer);
        }
        System.out.println(maxHeap.size());  // 8
        System.out.println(list1);  // [1, 2, 3, 5, 6, 7, 8, 9]
        System.out.println(list2);  // [9, 8, 7, 6, 5, 1, 3, 2]
    }

    public static void main(String[] args) {
        PriorityQueueUsage priorityQueueUsage = new PriorityQueueUsage();
        priorityQueueUsage.useHeap();
    }
}
