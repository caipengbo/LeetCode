//
// Pre-compile file
// Created by Myth on 4/1/2019.
//

#ifndef ALGOEX_PCH_H
#define ALGOEX_PCH_H

#include <iostream>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <algorithm>
#include <set>
#include <sstream>
#include <fstream>
using namespace std;

// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
void trimLeftTrailingSpaces(string &input);
void trimRightTrailingSpaces(string &input);
// [1, 2, 3, 4, 5] -> vector
vector<int> stringToIntegerVector(string input);
// [1, 2, 3, 4, 5] -> vector -> ListNode
ListNode* stringToListNode(string input);
void prettyPrintLinkedList(ListNode* node);
void prettyPrintVector(vector<int> v);
string treeNodeToString(TreeNode* root);
// [1, 2, 3, 4, 5] -> Tree
TreeNode* stringToTreeNode(string input);
void prettyPrintTree(TreeNode*, string, bool);

#endif //ALGOEX_PCH_H

