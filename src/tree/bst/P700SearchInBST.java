package tree.bst;

import util.TreeNode;

/**
 * Title: 700. 二叉搜索树中的搜索
 * Desc:
 * Created by Myth on 10/3/2019
 */
public class P700SearchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if (root.val == val) return root;
        if (root.val < val) return searchBST(root.left, val);
        return searchBST(root.right, val);
    }
    // 迭代版本也很简单
}
