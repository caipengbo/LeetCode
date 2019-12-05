package tree;

import util.TreeNode;

/**
 * Title: 979. 在二叉树中分配硬币
 * Desc: N 枚硬币运到 N 个节点上
 * Created by Myth-PC on 2019-10-02
 */
public class P979DistributeCoinsInBinaryTree {
    // 自底向上（后序遍历）
    private int dis = 0;
    public int distributeCoins(TreeNode root) {
        dis = 0;
        require(root);
        return dis;
    }
    // 当前节点及其子树所需的硬币数目（val+left+right-1）
    private int require(TreeNode root) {
        if (root == null) return 0;
        int left = require(root.left);
        int right = require(root.right);
        // 每个子树需要与父节点交换的数目
        dis += Math.abs(left) + Math.abs(right);
        return root.val+left+right-1;
    }
}
