package tree.path;

import util.TreeNode;

/**
 * Title: 236. 二叉树的最近公共祖先
 * Desc: 本题比235稍微难一些，235题可以通过数值的大小判断左右子树，该题不是BST不行
 * Created by Myth on 9/30/2019
 */
public class P236LowestCommonAncestorOfBinaryTree {
    // 递归1：
    private TreeNode lca;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca = null;
        find(root, p, q);
        return lca;
    }
    // 左边，右边，或者当前节点，有两个能找到p或者q，那么此节点就是公共祖先
    private boolean find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        int left = find(root.left, p, q) ? 1 : 0;
        int right = find(root.right, p, q) ? 1 : 0;
        int mid = (p == root || q == root) ? 1: 0;
        if (left + right + mid >= 2) lca = root;
        return (left + right + mid) > 0;
    }
    // 递归2：对每个节点对应的子树，若该子树不含有p或q，返回nullptr；
    // 否则，如果p和q分别位于当前子树根节点两侧，则返回当前节点，
    // 否则（p和q在同一侧，或者只有某一侧有p或q）返回来自左边或右边的LCA。
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;
        // 左边存在p或者q
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        // 右边存在p或者q
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        // p,q分别位于两侧
        if (left != null && right != null) return root;
        return (left == null) ? right : left;
    }

}
