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
        if (root != NULL) {
            sum += getSum(root);
            sum += sumRootToLeaf(root->left);
            sum += sumRootToLeaf(root->right);
            cout << "--" << sum << endl;
        }
        return sum;

    }
    int getSum(TreeNode*& node) {
        if (node->left == NULL && node ->right == NULL) {
            return (node->val) % (10^9 + 7);
        }
        if (node->left != NULL) {
            node->left->val = node->val * 2 + node->left->val;
        }
        if (node->right != NULL ) {
            node->right->val = node->val * 2 + node->right->val;
        }
        return 0;
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