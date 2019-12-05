package tree.path;

import util.TreeNode;
import util.TreeUtil;

/**
 * Title: 124. 二叉树中的最大路径和
 * Desc: 给定一个非空二叉树，返回其最大路径和。
 *本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * Created by Myth on 9/24/2019
 */
public class P124BinaryTreeMaximumPathSum {
    //
    private int maxSum;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        maxSum = root.val;
        arrowMaxPath(root);
        return maxSum;
    }
    private int arrowMaxPath(TreeNode root) {
        if (root == null) return 0;
        int left = arrowMaxPath(root.left);
        int right = arrowMaxPath(root.right);
        // 如何 累加值
        left = (left > 0 ? left + root.val : root.val);
        right = (right > 0 ? right + root.val : root.val);

        maxSum = Math.max(maxSum, left+right-root.val);
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root1 = TreeUtil.stringToTreeNode("[1,2,3]");
        TreeNode root2 = TreeUtil.stringToTreeNode("[2,-1]");
        TreeNode root3 = TreeUtil.stringToTreeNode("[-10,9,20,null,null,15,7]");
        TreeNode root4 = TreeUtil.stringToTreeNode("[1,-2,3]");
        P124BinaryTreeMaximumPathSum p124 = new P124BinaryTreeMaximumPathSum();
        // System.out.println(p124.maxPathSum(root1));
        System.out.println(p124.maxPathSum(root4));
    }
}
