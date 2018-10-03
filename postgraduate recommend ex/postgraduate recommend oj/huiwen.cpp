// 判断最大回文串
#include<iostream>
#include<string>
#include<algorithm>
using namespace std;
#define MAX_SIZE 1000
int huiwen(string s)
{
	int len = s.length();
	bool hui[MAX_SIZE][MAX_SIZE] = { 0 };
	int max_val = 1;

	for (int j = 0; j<len; j++) {
		for (int i = 0; i <= j; i++) {
			if (i == j) {
				hui[i][j] = 1;
			}
			else {
				// 注意此处的判断顺序
				if (s[i] == s[j] && ( j-i<=2|| hui[i+1][j-1] )) {
					hui[i][j] = 1;
					max_val = max(j-i+1,max_val);
				}
			}
		}
	}
	return max_val;
}
/*
int main()
{
	string s;
	while (cin >> s) {
		cout << huiwen(s) << endl;
	}
}
*/
