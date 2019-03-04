#include<iostream>
using namespace std;
#include<string>
#include<vector>
#include<set>
#include<map>
#include<algorithm>
class Solution {
public:
	vector<string> commonChars(vector<string>& A) {
		int arr[100][26];
		int i, j, k;
		int min;
		int len = A.size();
		vector<string> result;
		for (i = 0; i < 100; i++) {
			for (j = 0;  j < 26;  j++) {
				arr[i][j] = 0;
			}
		}
		for (i = 0; i < len; i++) {
			for (j = 0; j < A[i].size(); j++) {
				int index = A[i][j] - 'a';
				arr[i][index]++;
			}
		}

		for (i = 0; i < 26; i++) {
			min = arr[0][i];
			for (j = 0; j < len; j++) {
				if (arr[j][i] < min)
					min = arr[j][i];
			}
			for (k = 0; k < min; k++) {
				string s;
				s.assign(1, 'a' + i);  // 还可以改进
				result.push_back(s);
			}
		}
		return result;
	}
	bool isValid(string& S) {
		int len = S.size();
		if (len % 3 != 0) return false;
		string::iterator it;
		bool is_erased = true;
		while (is_erased) {
			is_erased = false;
			for (it = S.begin(); it + 2 < S.end(); it++) {
				if (*it == 'a' && *(it + 1) == 'b' && *(it + 2) == 'c') {
					it = S.erase(it, it + 3);
					// 一定要加此判断语句，防止下标越界
					if (S.size() < 3) break;
					is_erased = true;
				}
			}

		}
		return S.size() == 0;
	}
};
/*

int main()
{
	vector<string> A = { "bella", "label", "roller" };
	Solution s;
	vector<string> B = s.commonChars(A);
	// cout << B.size();
	string S = "bacbac";
	string::iterator it;
	cout << s.isValid(S);
	// it = S.erase(S.begin(), S.begin() + 2);
	// cout << *it;
}
*/