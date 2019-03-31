#include<iostream>
using namespace std;
#include<string>
#include<vector>
#include<stack>
#include<map>
#include<algorithm>
class Solution {
public:
	bool canThreePartsEqualSum(vector<int>& A) {
		int len = A.size();
		int sum = 0;
		for (auto ele : A) {
			sum += ele;
		}
		if (sum % 3 != 0) return false;
		int part_sum = sum / 3;
		int part = 0;
		
		int count = 0;
		for (auto ele : A) {
			part += ele;
			if (part == part_sum) {
				count++;
				part = 0;
			}
		}
		return count == 3;
	}
	// 模拟 111...111 除法的运算的过程
	int smallestRepunitDivByK(int K) {
		// 已经计算出来的余数（如果重复出现的话，那么将会导致计算出来的商，无限循环）
		vector<bool> visit(K);
		int x = 1 % K; // 注意边界条件
		int result = 1;
		while (x) {
			x = (x * 10 + 1) % K;
			if (visit[x]) return -1; // 重复出现
			visit[x] = true;
			result++;
		}
		return result;
	}
	// A.size >= 2
	int maxScoreSightseeingPair(vector<int>& A) {
		int front = A[0] + 0;
		int sum = A[0] + 0 + A[1] - 1;
		int cur;
		for (int i = 1; i < A.size(); i++) {
			cur = A[i] - i + front;
			sum = max(sum, cur);
			front = max(front, A[i] + i);
		}
		
		return sum;
	}

};

//int main() {
//	Solution s;
//	vector<int> vec= { 1, 2 };
//	//cout << s.canThreePartsEqualSum(vec);
//	cout << s.smallestRepunitDivByK(19927);
//	// cout << s.maxScoreSightseeingPair(vec);
//}