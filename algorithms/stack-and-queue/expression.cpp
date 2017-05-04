/*
 表达式求值，在转化成中缀表达式的过程之中，实现运算

*/
#include<iostream>
#include "header.h"
#include<string>
#include<stack>
#include<queue>
using namespace std;
//根据运算符的优先级赋值
int precedence_value(char ch)
{
	int val = -100;
	switch (ch)
	{
	case '(': {    //左括号优先级最低
		val = -1;
		break;
	}
	case '+':
	case '-': {
		val = 1;
		break;
	}
	case '*':
	case '/': {
		val = 2;
		break;
	}
	default: {
		val = -100;
		break;
	}
	}
	return val;
}
//判断符号的优先级 a>b true 
bool higher_precedence(char a, char b)
{
	return precedence_value(a)>precedence_value(b);
}

int cal(int a,int b,char opr)
{
	int rst = 0;
	switch (opr)
	{
	case '+': {
		rst = a + b;
		break;
	}
	case '-': {
		rst = a - b;
		break;
	}
	case '*': {
		rst = a * b;
		break;
	}
	case '/': {
		rst = a / b;
		break;
	}
	}
	return  rst;
}
//中缀infix转化成后缀postfix表达式 保存至队列中 Shunting-yard algorithm
queue<string> convert(string infix) {
	stack<char> opr_stack;
	queue<string> postfix;
	string unit = "";
	int i = 0;
	while (i < infix.size())
	{
		//处理多位数字
		while (i < infix.size() && infix[i] >= '0' && infix[i] <= '9')
		{
			unit += infix[i];
			i++;
		}
		if (unit != "")
		{
			postfix.push(unit);
			unit = "";
		}
		if (i >= infix.size()) break;  //检测下一个位置是否越界
									   //遇到右括号，弹出操作符栈中元素，直至栈顶为 左括号
		if (infix[i] == ')')
		{

			while (true)
			{
				if (opr_stack.top() == '(')
				{
					opr_stack.pop();
					break;
				}
				string s(1, opr_stack.top());
				postfix.push(s);
				opr_stack.pop();
			}
		}
		else if (infix[i] == '(' || opr_stack.empty()) {
			//操作符栈为空当前操作符为'('时，操作符直接入栈
			opr_stack.push(infix[i]);
		}
		else if (higher_precedence(infix[i], opr_stack.top())) {
			//栈顶不为空的前提下、当前操作符优先级大于栈顶操作符
			opr_stack.push(infix[i]);
		}
		else
		{
			//当前操作符优先级小于栈顶操作符，弹出栈顶 当前操作符不入栈(因为不知新的栈顶和当前操作符的优先级关系，continue一下，重新判断一下)
			string s(1, opr_stack.top());
			postfix.push(s);
			opr_stack.pop();
			continue; //将不会i++,继续从头执行比较一下当前操作符与新栈顶的元素优先级关系
					  //opr_stack.push(infix[i]);  
		}
		i++;
	}

	while (!opr_stack.empty())
	{
		string s(1, opr_stack.top());
		postfix.push(s);
		opr_stack.pop();
	}
	return postfix;
}

//中缀infix转化成后缀postfix表达式 保存至队列中 Shunting-yard algorithm
int calcaulate(string infix) {
	stack<int> value_stack;
	stack<char> opr_stack;
	string unit = "";
	unsigned int i = 0;
	int a, b;//两个临时的值用于保存栈顶次栈顶
	while (i < infix.size())
	{
		//处理多位数字
		while (i < infix.size() && infix[i] >= '0' && infix[i] <= '9')
		{
			unit += infix[i];
			i++;
		}
		if (unit != "")
		{
			value_stack.push(stoi(unit));
			cout << "入栈：" << stoi(unit) << "栈顶" << value_stack.top()<<endl;
			unit = "";
		}
		if (i >= infix.size()) break;  //检测下一个位置是否越界
									  						  
		if (infix[i] == ')')  //遇到右括号，弹出操作符栈中元素，直至栈顶为 左括号
		{

			while (true)
			{
				if (opr_stack.top() == '(')
				{
					opr_stack.pop();
					break;
				}
				a = value_stack.top();
				value_stack.pop();
				b = value_stack.top();
				value_stack.pop();
				value_stack.push(cal(b, a, opr_stack.top()));  //注意是 次栈顶 在前，栈顶在后
				cout << "求值：" << "栈顶" << value_stack.top() << endl;
				opr_stack.pop();
			}
		}
		else if (infix[i] == '(' || opr_stack.empty()) {
			//操作符栈为空当前操作符为'('时，操作符直接入栈
			opr_stack.push(infix[i]);
		}
		else if (higher_precedence(infix[i], opr_stack.top())) {
			//栈顶不为空的前提下、当前操作符优先级大于栈顶操作符
			opr_stack.push(infix[i]);
		}
		else
		{
			a = value_stack.top();
			value_stack.pop();
			b = value_stack.top();
			value_stack.pop();
			value_stack.push(cal(b, a, opr_stack.top()));
			cout << "求值：" << "栈顶" << value_stack.top() << endl;
			opr_stack.pop();
			continue; 
		}
		i++;
	}

	while (!opr_stack.empty())
	{
		a = value_stack.top();
		value_stack.pop();
		b = value_stack.top();
		value_stack.pop();
		value_stack.push(cal(b, a, opr_stack.top()));
		cout << "求值：" << "栈顶" << value_stack.top() << endl;
		opr_stack.pop();
	}
	return value_stack.top();
}

