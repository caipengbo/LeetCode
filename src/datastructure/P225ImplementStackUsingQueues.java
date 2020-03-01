package datastructure;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

/**
* Title: 225. 用队列实现栈
* Desc: 
* Created by Myth-PC on 01/03/2020 in VSCode
*/
public class P225ImplementStackUsingQueues {
    // 使用两个栈
    Queue<Integer> dataQueue = new LinkedList<>();
    Queue<Integer> swapQueue = new LinkedList<>();
    int peek = -1;

    public void push(int x) {
        dataQueue.add(x);
        peek = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int cur = -1;
        while (!dataQueue.isEmpty()) {
            cur = dataQueue.poll();
            if (!dataQueue.isEmpty()) {
                swapQueue.add(cur);
                peek = cur;
            }
        }
        Queue<Integer> temp = dataQueue;
        dataQueue = swapQueue;
        swapQueue = temp; 
        return cur;
    }
    
    /** Get the top element. */
    public int top() {
        return peek;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return dataQueue.isEmpty();
    }
    // 优化 使用一个栈
    Queue<Integer> queue = new LinkedList<>();
    
    // 
    public void push2(int x) {
        
    }
    public int pop2() {
        int n = queue.size();
        for (int i = 0; i < n-1; i++) {
            queue.add(queue.poll());
        }
        return queue.poll();
    }

}