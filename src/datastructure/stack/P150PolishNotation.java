package datastructure.stack;

import java.util.Stack;

/**
 * Title: 150. 逆波兰表达式求值 
 * Desc: 根据 逆波兰表示法，求表达式的值。有效的运算符包括 +, -, *, /。
 * 每个运算对象可以是整数，也可以是另一个逆波兰表达式。 
 * Created by Myth-MBP on 06/07/2020 in VSCode
 */
public class P150PolishNotation {
   
	public static int evalRPN(String[] tokens) {
		Stack<Integer> numStack = new Stack<>();
		Integer op1, op2;
		for (String s : tokens) {
			switch (s) {
			case "+":
				op2 = numStack.pop();
				op1 = numStack.pop();
				numStack.push(op1 + op2);
				break;
			case "-":
				op2 = numStack.pop();
				op1 = numStack.pop();
				numStack.push(op1 - op2);
				break;
			case "*":
				op2 = numStack.pop();
				op1 = numStack.pop();
				numStack.push(op1 * op2);
				break;
			case "/":
				op2 = numStack.pop();
				op1 = numStack.pop();
				numStack.push(op1 / op2);
				break;
			default:
				numStack.push(Integer.valueOf(s));
				break;
			}
		}
		return numStack.pop();
	}
}