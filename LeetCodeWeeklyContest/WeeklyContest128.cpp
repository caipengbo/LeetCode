#include<iostream>
using namespace std;
#include<string>
#include<vector>
#include<stack>
#include<map>
#include<algorithm>
// https://leetcode.com/contest/weekly-contest-128/
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
		/*
		// 方法2
		reverse(bin_vec.begin(), bin_vec.end());
		for (auto it : bin_vec) {
			it = 1 - it; // 取反
			sum = sum * 2 + it;
		}
		*/

		return sum;
	}
	int numPairsDivisibleBy60(vector<int>& time) {
		int count = 0;
		vector<int> fre(60,0);
		int mod;
		/*for (int i = 0; i < time.size(); i++) {
			mod = time[i] % 60;
			fre[mod]++;
		}*/
		for(auto it : time) {
			fre[it % 60]++;
		}

		for (int i = 1; i < 30; i++) {
			count += fre[i] * fre[60 - i];
		}
		count += ((fre[0] * (fre[0]-1)) + fre[30] * (fre[30]-1))/2;
		return count;
	}
	// 找一个划分值 =》在一段范围中寻找一个合适的值 -> 二分查找
	// 详解：http://t.cn/ExixHrG
	int shipWithinDays(vector<int>& weights, int D) {
		int low = 0, hight = 0;
		for (auto it : weights ) {
			hight += it;
			low = max(low, it);
		}
		low = max(low, hight/D);
		while (low < hight) {
			int cnt = 0, sum = 0;
			int mid = (low + hight) / 2;
			// 此处求所用的天数，耗费了很多的时间
			for (auto it : weights) {
				if (sum + it > mid) {
					cnt++;
					sum = it;
				} else {
					sum += it;
				}
			}
			cnt++;
			// cout << cnt << "-----"<< endl;
			if (cnt > D) low = mid + 1;
			else hight = mid;
		}
		return low;
	}
	// 调试划分
	void huafen(vector<int>& weights, int D) {
		int cnt = 0, sum = 0;
		int mid = 602;
		for (auto it : weights) {
			if (sum + it > mid) {
				cnt++;
				cout << endl;
				sum = it;
				cout << it << ", ";
			} else {
				cout << it << ", ";
				sum += it;
			}
			
		}
	}
	// 划分方法二：使用了两个循环（循环的条件很巧妙，实际上是将一个循环拆开了）
	void huafen2(vector<int>& weights, int D) {
		int cnt = 0;
		int mid = 602;
		for (int i = 0, j; i < weights.size(); i = j) {
			int sum = 0;
			for (j = i; j < weights.size(); j++) {
				if (sum + weights[j] > mid) {
					cout << endl;
					break;
				}
				cout << weights[j] << ",";
				sum += weights[j];
			}
			++cnt;
		}
			
	}
	int shipWithinDays2(vector<int>& weights, int D) {
		int left = 0, right = 25000000;
		for (int w : weights)
			left = max(left, w);
		while (left < right) {
			int mid = (left + right) / 2, need = 1, cur = 0;
			for (int i = 0; i < weights.size() && need <= D; cur += weights[i++])
				if (cur + weights[i] > mid)
					cur = 0, need++;
			cout << need << "=====" << endl;
			if (need > D) left = mid + 1;
			else right = mid;
		}
		return left;
	}
	//int numDupDigitsAtMostN(int N) {
	//	int count = 0;
	//	vector<int> number;
	//	while (N / 10 != 0) {
	//		number.push_back(N % 10);
	//		N = N / 10;
	//	}
	//	number.push_back(N % 10);
	//	printVector(number);
	//	count = countVec(number);
	//	return count;
	//}
	//int countVec(vector<int> v) {
	//	int len = v.size();
	//	if (len <= 1) {
	//		return 0;
	//	}
	//	int count = 0;
	//	// 最高位 小于最高位数字 时
	//	count += (v[len-1]) * ((len - 1) * (len - 2) / 2 * 10);
	//	// 等于最高位数字时
	//	vector<int> v_sub(v.begin(), v.end() - 1);
	//	count += countVec(v_sub);
	//	cout << "--Print sub count: " << count << endl;
	//	return count;
	//}

	void printVector(vector<int> v) {
		cout << "Print Vector: ";
		for (auto& val : v) {
			cout << val << ", ";
		}
		/*for (vector<int>::iterator i = v.begin(); i != v.end(); i++) {
			cout << *i << ", ";
		}*/
		cout << endl;
	}
	
	
};

//int main() {
//	int x = 10;
//	Solution s;
//	vector<int> time = { 147, 73, 265, 305, 191, 152, 192, 293, 309, 292, 182, 157, 381, 287, 73, 162, 313, 366, 346, 47 };
//	// cout << s.numPairsDivisibleBy60(time) << endl;
//	cout << s.shipWithinDays(time, 10) << endl;
//	// s.huafen(time, 10);
//	// cout << "==========" << endl;
//	// s.huafen2(time, 10);
//}