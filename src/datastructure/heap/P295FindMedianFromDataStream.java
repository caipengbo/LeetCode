package datastructure.heap;

import java.util.PriorityQueue;

/**
* Title: 295. 数据流中的中位数
* Desc: 中位数是排序后中间的数
* Created by Myth-PC on 14/02/2020 in VSCode
*/
public class P295FindMedianFromDataStream {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2-o1);
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    public P295FindMedianFromDataStream() {
        
    }
    
    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if (minHeap.size() > maxHeap.size()) maxHeap.add(minHeap.poll());
    }
    
    public double findMedian() {
        if (maxHeap.size() == 0) return 0.0;  // 为空
        if (maxHeap.size() == minHeap.size()) return (maxHeap.peek()+minHeap.peek()) / 2.0;
        else return (double)maxHeap.peek();
    }

}