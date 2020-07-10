package datastructure.monotonicqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


/**
* Title: 队列的最大值
* Desc: 
* Created by Myth-MBP on 09/07/2020 in VSCode
*/

public class MaxInQueue {
    /**
     * 求队列的最大值，实现 max  push_back  pop_front 函数。
    思路：设置两个队列，一个普通队列Queue保存数据，一个Deque保存最大值（单调递减栈）。
    Push的时候若新元素大于Deque则弹出Deque的最后一个元素，添加新元素，否则的话直接添加新元素。
    Pop的时候，如果Deque队首等于要pop的Queue队首元素，那么Deque也pop掉该元素，否则不用pop Deque。

    > Warning: 本题出现了一个重大的错误，由于使用Deque，元素是Integer，使用了 == 去比较了（Integer有缓存池，小于255的才会==，否则是不相等的），导致出现了错误！！！
     */
    Queue<Integer> queue = new LinkedList<>();
    Deque<Integer> deque = new LinkedList<>();  // 存储最大值
    public int max_value() {
        if (deque.isEmpty()) return -1;
        return deque.peek();
    }
    
    public void push_back(int value) {
        while (!deque.isEmpty() && value > deque.getLast()) deque.removeLast();
        deque.add(value);
        queue.add(value); 
    }
    
    public int pop_front() {
        if (queue.isEmpty()) return -1;
        // 此处千万不要使用==去判断
        if (deque.peek().equals(queue.peek())) deque.removeFirst();
        return queue.remove();
    }
}