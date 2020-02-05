package tree.structure;

import util.TreeNode;

/**
 * Title: 226. 反转二叉树 
 * Desc: 
 * Created by Myth-PC on 05/02/2020 in VSCode
 */
public class P226InvertBinaryTree {
    // 这种写法不太好，以为返回值显得很多余
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root; 
    }
    public void invertTree2(TreeNode root) {
        if (root == null) return;
        // 前序遍历（可改成中序或者后序遍历）
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree2(root.left);
        invertTree2(root.right);
    }
    // 实际上这是一个简洁版的中序遍历
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root; 
    }

}