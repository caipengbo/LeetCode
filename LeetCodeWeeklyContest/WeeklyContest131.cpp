//
// Created by Myth on 4/7/2019.
//

#include "pch.h"

class Solution {
public:
    string removeOuterParentheses(string S) {
        stack<char> stk;
        string ret;
        for (int i = 0; i < S.length(); ++i) {
            if(S[i] == '(') {
                if (!stk.empty()) {
                    ret += S[i];
                }
                stk.push('(');
            } else {
                if (stk.size() != 1) {
                    ret += S[i];
                }
                stk.pop();
            }
        }
        return ret;
    }
    string removeOuterParentheses2(string S) {
        int sum = 0, n = S.size();
        int last = 0;
        string ret;
        for (int i = 0; i < n; ++i) {
            sum += (S[i] == '(' ? 1 : -1); // 成对儿，sum = 0
            if (sum == 0) {
                string cur = S.substr(last+1, i-last-1);
                ret += cur;
                last = i + 1;
            }
        }
        return ret;
    }

    /**
    * Definition for a binary tree node.
    * struct TreeNode {
    *     int val;
    *     TreeNode *left;
    *     TreeNode *right;
    *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    * };
    */
    int sumRootToLeaf(TreeNode*& root) {
        int sum = 0;
        solve(root, sum);
        return sum;

    }
    void solve(TreeNode*& node, int& sum) {
        if (node == NULL) return;
        if (node->left == NULL && node ->right == NULL) {
            sum = (sum + node->val) % (10^9 + 7);
        }
        if (node->left != NULL) {
            node->left->val = (node->val * 2) % (10^9 + 7) + node->left->val;
            solve(node->left, sum);
        }
        if (node->right != NULL ) {
            node->right->val = (node->val * 2) % (10^9 + 7) + node->right->val;
            solve(node->right, sum);
        }
    }

    /**
    vector<bool> camelMatch(vector<string>& queries, string pattern) {

    }
     */

};
int main() {
    Solution s;
    // cout << s.removeOuterParentheses("(()())(())(()(()))");
    TreeNode* root = stringToTreeNode("[1,1,1,null,null,0,1,null,null,1,1,null,null,null,1]");
    cout << s.sumRootToLeaf(root);
    cout << treeNodeToString(root);
}