/*
	算法：大数乘法
	思路： 模拟普通乘法相加
	反转后，进行各位相乘，求得和，用一个vector作为中间和
	每次求得的和与此中间和相加(注意相加的起始位置)
	作者: Myth
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;
// return a * b
vector<int> multiple(string x, string y)
{
	vector<int> a, b, result;
	int i;
	int size_a = x.length();
	int size_b = y.length();
	reverse(x.begin(), x.end());
	reverse(y.begin(), y.end());

	//将字符转换成数字，存入vector
	for (i = 0; i<size_a; i++)
	{
		a.push_back((int)(x[i] - '0'));
	}
	for (i = 0; i<size_b; i++)
	{
		b.push_back((int)(y[i] - '0'));
	}

	int min_size = 0;
	int c = 0;//进位

	min_size = (size_a > size_b ? size_b : size_a);

	for (i = 0; i<min_size; i++)
	{
		if ((a[i] + b[i] + c) >= 10)
		{
			result.push_back((a[i] + b[i] + c) - 10);
			c = 1;
		}
		else
		{
			result.push_back(a[i] + b[i] + c);
			c = 0;
		}
	}
	//处理剩下的位
	if (size_a > size_b)
	{
		for (i = min_size; i<size_a; i++)
		{
			if ((a[i] + c) >= 10)
			{
				result.push_back(a[i] + c - 10);
				c = 1;
			}
			else
			{
				result.push_back(a[i] + c);
				c = 0;
			}
		}
	}
	else
	{
		for (i = min_size; i<size_b; i++)
		{
			if ((b[i] + c) >= 10)
			{
				result.push_back(b[i] + c - 10);
				c = 1;
			}
			else
			{
				result.push_back(b[i] + c);
				c = 0;
			}
		}
	}
	if (c == 1)
		result.push_back(c);

	reverse(result.begin(), result.end());
	return result;
}


