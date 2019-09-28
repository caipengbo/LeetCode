package tree.path;

import util.TreeNode;

/**
 * Title: 543. 二叉树的直径
 * Desc: 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示
 * Created by Myth on 9/28/2019
 */
public class P543DiameterPathOfBinaryTree {
    // 和 124、687 类型相似
    private int maxSum;
    public int diameterOfBinaryTree(TreeNode root) {
        maxSum = 0;
        helper(root);
        return maxSum;
    }
    // 求单侧的路径长度
    public int helper(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return 0;
        int left = helper(root.left)+1;
        int right = helper(root.right)+1;
        maxSum = Math.max(maxSum, left+right);
        return Math.max(left, right);
    }
    // 其他写法
    public int diameterOfBinaryTree2(TreeNode root) {
        maxSum = 1;
        helper(root);
        return maxSum-1;
    }
    public int helper2(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        maxSum = Math.max(maxSum, left+right+1);
        return Math.max(left, right)+1;
    }

    public static void main(String[] args) {
        P543DiameterPathOfBinaryTree p543 = new P543DiameterPathOfBinaryTree();
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1, node2, node3);
        System.out.println(p543.helper(node1));
    }
}
