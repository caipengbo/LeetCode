package datastructure.stack;

import java.util.Stack;

/**
 * Title: 剑指offer31： 
 * Desc: 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的） 
 * Created by Myth-MBP on 09/07/2020
 * in VSCode
 */
public class StackPushPopOrder {
    // 创建一个辅助栈，模拟入栈和出栈的顺序，如果最后辅助栈为空，那么弹出栈是压入栈的序列
    public boolean isPopOrder(int [] pushA,int [] popA) {
        java.util.Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        while (i < pushA.length) {
            stack.push(pushA[i++]);
            while (!stack.empty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.empty();
    }
}