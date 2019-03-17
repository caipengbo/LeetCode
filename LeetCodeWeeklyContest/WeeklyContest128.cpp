#include<iostream>
using namespace std;
#include<string>
#include<vector>
#include<stack>
#include<map>
#include<algorithm>
class Solution {
public:
	int bitwiseComplement(int N) {
		if (N == 0) {
			return 1;
		}
		vector<bool> bin_vec;
		int sum = 0;
		while (N != 0) {
			bin_vec.push_back(N % 2);
			N = N / 2;
		}
		for (int i = 0; i < bin_vec.size(); i++) {
			if (!bin_vec[i]) {
				sum += pow(2, i);
			}
		}
		return sum;
	}
	int numPairsDivisibleBy60(vector<int>& time) {
		int count = 0;
		vector<int> fre(60,0);
		int mod;
		for (int i = 0; i < time.size(); i++) {
			mod = time[i] % 60;
			fre[mod]++;
		}
		for (int i = 1; i < 30; i++) {
			count += fre[i] * fre[60 - i];
		}
		count += ((fre[0] * (fre[0]-1)) + fre[30] * (fre[30]-1))/2;
		return count;
	}
	int numDupDigitsAtMostN(int N) {
		int count = 0;
		vector<int> number;
		while (N / 10 != 0) {
			number.push_back(N % 10);
			N = N / 10;
		}
		number.push_back(N % 10);
		printVector(number);
		count = countVec(number);
		return count;
	}
	int countVec(vector<int> v) {
		int len = v.size();
		if (len <= 1) {
			return 0;
		}
		int count = 0;
		// 最高位 小于最高位数字 时
		count += (v[len-1]) * ((len - 1) * (len - 2) / 2 * 10);
		// 等于最高位数字时
		vector<int> v_sub(v.begin(), v.end() - 1);
		count += countVec(v_sub);
		cout << "--Print sub count: " << count << endl;
		return count;
	}

	void printVector(vector<int> v) {
		cout << "Print Vector: ";
		for (vector<int>::iterator i = v.begin(); i != v.end(); i++) {
			cout << *i << ", ";
		}
		cout << endl;
	}
	
	
};

int main() {
	int x = 10;
	Solution s;
	vector<int> time = { 60, 60 , 60 };
	// cout << s.numPairsDivisibleBy60(time) << endl;
	s.numDupDigitsAtMostN(1000);
}