/*
	算法:	大数加法
	思路:	模拟普通加法运算
			将字符串反转后，存入两vector之中，然后对应相加即可
	作者:	Myth
	注意:	注意vector如何添加元素（切勿越界访问，直接赋值）
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;
// return a + b
vector<int> add(string x, string y)
{
	vector<int> a,b,result;
	int i;
	int size_a = x.length();
	int size_b = y.length();
	reverse(x.begin(),x.end());
	reverse(y.begin(),y.end());
	for (i=0; i<size_a; i++)
	{
		a.push_back((int)(x[i] - '0'));
	}
	for (i=0; i<size_b; i++)
	{
		b.push_back((int)(y[i] - '0'));
	}

	int min_size = 0;
	int c = 0;//进位

	min_size = (size_a > size_b ? size_b : size_a);

	for (i=0; i<min_size; i++)
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
		for (i=min_size; i<size_a; i++) 
		{
			if ((a[i] + c) >= 10)
			{
				result.push_back(a[i] + c - 10);
				c = 1;
			} else
			{
				result.push_back(a[i] + c);
				c = 0;
			}
		}
	} 
	else
	{
		for (i=min_size; i<size_b; i++) 
		{
			if ((b[i] + c) >= 10)
			{
				result.push_back(b[i] + c - 10);
				c = 1;
			} else
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

int main ()
{
	string x,y;
	vector<int> result;
	cin >> x >> y;
	result = add(x,y);
	vector<int>::iterator it;
	for (it=result.begin(); it<result.end(); it++)
		cout  << *it;
	cout<<endl;

}
