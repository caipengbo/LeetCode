package tree.pruning;

import util.TreeNode;

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