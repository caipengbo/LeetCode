package tree.traversal;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Title: 145. 二叉树的后序遍历
 * Desc: 后序遍历的迭代算法和递归算法
 * Created by Myth on 9/14/2019
 */
public class P145BinaryTreePostorderTraversal {
    // 递归
    public void postorder(TreeNode root, List<Integer> ret) {
        if (root == null) return;
        postorder(root.left, ret);
        postorder(root.right, ret);
        ret.add(root.val);
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        postorder(root, ret);
        return ret;
    }
    // 非递归算法（前序遍历的顺序是 根-左-右，后序遍历的顺序是 左-右-根）
    // 那么 前序稍微修改一下 变成 根-右-左，然后把结果倒序，就变成 左-右-根 了
    public List<Integer> postorderIterative(TreeNode root) {
        // 使用LinkedList的头插，让结果倒序
        LinkedList<Integer> ret = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        stack.add(cur);
        while (!stack.empty()) {
            cur = stack.pop();
            if (cur != null) {
                ret.addFirst(cur.val);  // 头插法，让结果倒序
                stack.add(cur.left);
                stack.add(cur.right);
            }
        }
        return ret;
    }

}
