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
		int part1 = 0;
		
		int count = 0;
		for (auto ele : A) {
			part1 += ele;
			if (part1 == part_sum) {
				count++;
				part1 = 0;
			}
		}
		return count == 3;
	}
	int smallestRepunitDivByK(int K) {
		if (K % 2 == 0 || K % 5 == 0) return -1;
		
		return -1;
	}
	int maxScoreSightseeingPair(vector<int>& A) {
		return 0;
	}

};

int main() {
	Solution s;
	vector<int> vec= { 1, 2 };
	//cout << s.canThreePartsEqualSum(vec);
	cout << s.smallestRepunitDivByK(19927);
	// cout << s.maxScoreSightseeingPair(vec);
}