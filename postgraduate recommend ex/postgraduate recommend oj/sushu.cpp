//ÅĞ¶ÏËØÊı
#include<iostream>
using namespace std;
bool prime(int n)
{
	if (n < 2) {
		return false;
	}
	int i = 2;
	while (i*i <= n) {
		if (n%i == 0)
			break;
		++i;
	}
	return (i*i > n);
}
void test()
{
	int n;
	while (cin >> n)
	{
		if (prime(n)) 
		{
			cout << "yes" << endl;
		}
		else {
			cout << "no" << endl;
		}

	}

}