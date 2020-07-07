package datastructure.stack;

import java.util.Stack;

/**
* Title: 155. 最小栈（可以求最小值的栈）
* Desc: 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
    push(x) —— 将元素 x 推入栈中。
    pop() —— 删除栈顶的元素。
    top() —— 获取栈顶元素。
    getMin() —— 检索栈中的最小元素。
* Created by Myth-PC on 05/02/2020 in VSCode
*/
public class P155MinStack {

    private Stack<Integer> dataStack = new Stack<>();
    // 使用一个minStack存储最小值
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        if (minStack.empty() || x <= minStack.peek()) {
            minStack.push(x);
        } else {
            // 当前最小值仍然是最小值
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