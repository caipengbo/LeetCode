package tree.pruning;

import util.TreeNode;

/**
* Title: 814. 二叉树剪枝
* Desc: 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
* 返回移除了所有不包含 1 的子树的原二叉树。(节点 X 的子树为 X 本身，以及所有 X 的后代。)
* Created by Myth-MBP on 04/06/2020 in VSCode
*/

public class P814BinaryTreePruning {
    // 判断是否含有1
    private boolean hasOne(TreeNode root) {
        if (root == null) return false;
        if (root.val == 1) return true;
        return hasOne(root.left) || hasOne(root.right);
    }
    public TreeNode pruneTree(TreeNode root) {
        if (!hasOne(root)) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return root;
    }
    // 后续遍历写法
    public TreeNode pruneTree2(TreeNode root) {
        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null)
            return null;
        return root;
    }
    
}