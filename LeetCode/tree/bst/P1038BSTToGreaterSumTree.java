package tree.bst;

import util.TreeNode;

/**
 * Title: 1038. 从二叉搜索树到更大的和树
 * Desc: 给出二叉搜索树的根节点，该二叉树的节点值各不相同，修改二叉树，
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * Created by Myth-PC on 2019-10-06
 */
public class P1038BSTToGreaterSumTree {
    // 本题依然是中序遍历，不一样的是先遍历右子树
    private TreeNode pre = null;
    public TreeNode bstToGst(TreeNode root) {
        pre = null;
        inorderDFS(root);
        return root;
    }
    private void inorderDFS(TreeNode root) {
        if (root == null) return;
        inorderDFS(root.right);
        if (pre != null) {
            root.val = root.val + pre.val;
        }
        pre = root;
        inorderDFS(root.left);
    }
}
