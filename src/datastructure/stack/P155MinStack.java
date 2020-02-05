package datastructure.stack;

import java.util.Stack;

/**
* Title: 155. 最小栈（可以求最小值的栈）
* Desc: 
* Created by Myth-PC on 05/02/2020 in VSCode
*/
public class P155MinStack {

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        if (minStack.empty() || x <= minStack.peek()) {
            minStack.push(x);
        } else {
            minStack.push(minStack.peek());
        }
        dataStack.push(x);
    }
    
    public void pop() {
        dataStack.pop();
        minStack.pop();
    }
    
    public int top() {
        return dataStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}