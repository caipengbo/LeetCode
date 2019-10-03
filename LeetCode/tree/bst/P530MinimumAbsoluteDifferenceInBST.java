package tree.bst;

import util.TreeNode;

import java.util.Stack;

/**
 * Title: 530. 二叉搜索树的最小绝对差
 * Desc: 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 * Created by Myth-PC on 2019-10-03
 */
public class P530MinimumAbsoluteDifferenceInBST {
    // 非递归写法：
    // BST的中序遍历是个有序序列，最小绝对差肯定存在于相邻元素之间，中序遍历，然后更新相邻元素差就行
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int ret = Integer.MAX_VALUE;  // 树中至少两个元素
        TreeNode pre = null;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null) ret = Math.min(ret, Math.abs(cur.val-pre.val));
            pre = cur;
            cur = cur.right;
        }
        return ret;
    }
    // 递归写法
    private int dis = Integer.MAX_VALUE;
    private TreeNode pre = null;
    public int getMinimumDifference2(TreeNode root) {
        dis = Integer.MAX_VALUE;
        pre = null;
        recursive(root);
        return dis;
    }
    private void recursive(TreeNode root) {
        if (root == null) return;
        recursive(root.left);
        if (pre != null) dis = Math.min(dis, Math.abs(root.val-pre.val));
        pre = root;
        recursive(root.right);
    }
}
