package tree.path;

import util.TreeNode;

/**
 * Title: 235. 二叉搜索树的最近公共祖先
 * Desc: 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * Created by Myth on 9/29/2019
 */
public class P235LowestCommonAncestorOfBST {
    // 该题求BST的最近公共祖先，难度显著降低，BST有明显的特点
    // 遍历树，如果p,q都在左（右）子树，那么就从左（右）子树进行递归，否则就找到了LCA
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
    // 迭代写法：循环找到一个节点（分割点），使得该节点将p 和 q分开
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p.val < cur.val && q.val < cur.val) cur = cur.left;
            else if (p.val > cur.val && q.val > cur.val) cur = cur.right;
            else return cur;
        }
        return cur;
    }
}
