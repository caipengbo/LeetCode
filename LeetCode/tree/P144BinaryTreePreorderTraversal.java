package tree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    // 非递归（队列）
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        while (root != null || !queue.isEmpty()) {

        }

        return ret;
    }
}
