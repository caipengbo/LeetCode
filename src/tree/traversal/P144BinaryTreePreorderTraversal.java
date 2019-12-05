package tree.traversal;

import util.TreeNode;
import util.TreeUtil;

import java.util.*;

/**
 * Title: 二叉树的前序遍历
 * Desc: 给定一个二叉树，返回它的 前序 遍历。
 * Created by Myth on 9/13/2019
 */
public class P144BinaryTreePreorderTraversal {
    // 递归
    public void preorder(TreeNode root, List<Integer> ret) {
        if (root == null) return;
        ret.add(root.val);
        preorder(root.left, ret);
        preorder(root.right, ret);
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        preorder(root, ret);
        return ret;
    }
    // 迭代 1
    public List<Integer> preorderIterative(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            ret.add(cur.val);
            if (cur.right != null) stack.add(cur.right);
            cur = cur.left;
            if (cur == null && !stack.isEmpty()) {
                cur = stack.pop();
            }
        }
        return ret;
    }
    // 迭代 2(√)
    public List<Integer> preorderIterative2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        stack.add(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (cur != null) {
                ret.add(cur.val);
                stack.add(cur.right);
                stack.add(cur.left);
            }
        }
        return ret;
    }
    // 迭代 3
    public List<Integer> preorderIterative3(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                ret.add(cur.val);
                stack.add(cur.right);
                cur = cur.left;
            } else {
                cur = stack.pop();
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        P144BinaryTreePreorderTraversal p144 = new P144BinaryTreePreorderTraversal();
        TreeNode root = TreeUtil.stringToTreeNode("[1,2,3]");
        p144.preorderIterative2(root);

    }
}
