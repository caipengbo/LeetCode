package tree.pruning;

import util.TreeNode;

/**
* Title: 669. 修剪二叉搜索树
* Desc: 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。
* 通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
* Created by Myth-MBP on 04/06/2020 in VSCode
*/

class P669TrimBinarySearchTree {
    // 重点在于如何剪枝，如何调整节点
    // 参考思路：< L , 只保留二叉树的右子树
    // > R, 只保留二叉树的左子树
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        // 调整节点(难点)
        // < L, 只保留二叉树的右子树(结果肯定在右边)
        if (root.val < L) return trimBST(root.right, L, R);
        
        // > R, 只保留二叉树的左子树(结果肯定在左边)
        if (root.val > R) return trimBST(root.left, L, R);
        
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}