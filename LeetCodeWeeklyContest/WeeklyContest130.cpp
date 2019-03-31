#include<iostream>
using namespace std;
#include<string>
#include<vector>
#include<stack>
#include<map>
#include<algorithm>

struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};
class Solution {
public:
	// error!!! too long!!!
	vector<bool> prefixesDivBy5Error(vector<int>& A) {
		int sum = 0;
		vector<bool> ret;
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j <= i; j++) {
				sum = sum * 2 + j;
			}
			if (sum % 5 == 0) {
				ret.push_back(true);
			} else {
				ret.push_back(false);
			}
			sum = 0;
		}
		return ret;
	}
	vector<bool> prefixesDivBy5(vector<int>& A) {
		vector<bool> ret;
		int mod = 0;
		for (int i = 0; i < A.size(); i++) {
			if (A[i]) mod = (mod * 2 + 1) % 5;
			else mod = (mod * 2) % 5;
			
			if (mod == 0) {
				ret.push_back(true);
			} else {
				ret.push_back(false);
			}
			
		}
		return ret;
	}

	string baseNeg2(int N) {
		if (N == 0) return "0";
		string ret;
		vector<int> ans;
		int num = 0;
		while (N) {
			ans.push_back(N % (-2));
			N /= (-2);
			if (ans[num] == -1) {
				ans[num] = 1; 
				N++;
			}
			num++;
		}
		for (vector<int>::reverse_iterator it = ans.rbegin(); it != ans.rend(); it++) {
			if (*it) {
				ret.push_back('1');
			} else {
				ret.push_back('0');
			}
		}
		return ret;
	}
	
	// https://leetcode.com/problems/next-greater-node-in-linked-list/
	vector<int> nextLargerNodes(ListNode* head) {
		stack<int> positionStack;
		vector<int> ret;
		for (ListNode* p = head; p != NULL; p = p->next) {
			while (positionStack.size() && ret[positionStack.top()] < p->val) {
				ret[positionStack.top()] = p->val;
				positionStack.pop();
			}
			positionStack.push(ret.size());
			ret.push_back(p->val);
		}
		while (!positionStack.empty()) {
			ret[positionStack.top()] = 0;
			positionStack.pop();
		}
		return ret;
	}
};
int main() {
	Solution s;
	cout << s.baseNeg2(2) << endl;
	cout << s.baseNeg2(3) << endl;
	cout << s.baseNeg2(4) << endl;
}