package tree.bst;

import util.TreeNode;
import util.TreeUtil;

/**
 * Title: 701. 二叉搜索树中的插入操作
 * Desc:
 * Created by Myth on 10/3/2019
 */
public class P701InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode cur = root;
        TreeNode pre;
        while (true) {
            pre = cur;
            if (cur.val > val) {
                cur = cur.left;
                if (cur == null) {
                    pre.left = new TreeNode(val);
                    break;
                }
            }
            else {
                cur = cur.right;
                if (cur == null) {
                    pre.right = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
    // 递归版本
    public TreeNode insertIntoBSTRecursive(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) root.right = insertIntoBSTRecursive(root.right, val);
        else root.left = insertIntoBSTRecursive(root.left, val);
        return root;
    }
    public static void main(String[] args) {
        P701InsertIntoBST p701 = new P701InsertIntoBST();
        TreeNode root = TreeUtil.stringToTreeNode("[4,2,7,1,3,6]");
        root = p701.insertIntoBST(root,5);
        TreeUtil.prettyPrintTree(root);
    }
}
