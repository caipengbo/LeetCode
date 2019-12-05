package tree.depth;

import util.TreeNode;

/**
 * Title: 104. 二叉树的最大深度
 * Desc: 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * Created by Myth-PC on 2019-09-18
 */
public class P104MaxDepthBinaryTree {
    // 递归思路：二叉树的最大深度 = max(左子树深度，右子树深度)
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right)+1;
    }
    // 递归 借助栈 变成迭代

}
