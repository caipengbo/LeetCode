package tree;

import util.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Title: 94. 二叉树的中序遍历(递归、非递归写法)
 * Desc: 给定一个二叉树，返回它的中序 遍历。
 * Created by Myth on 9/13/2019
 */
public class P94BinaryTreeInorderTraversal {
    // 递归写法
    public void inorder(TreeNode root, List<Integer> ret) {
        if (root == null) return;
        inorder(root.left, ret);
        ret.add(root.val);
        inorder(root.right, ret);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        inorder(root, ret);
        return ret;
    }
    // 非递归写法（借助于栈）
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ret.add(cur.val);
            cur = cur.right;
        }

        return ret;
    }

    public static void main(String[] args) {
        P94BinaryTreeInorderTraversal p94 = new P94BinaryTreeInorderTraversal();
        TreeNode root = TreeUtil.stringToTreeNode("[1,null,2,3]");
        System.out.println(p94.inorderTraversal2(root));
    }
}
