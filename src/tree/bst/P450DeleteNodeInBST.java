package tree.bst;

import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Title: 450. 删除二叉搜索树中的节点
 * Desc: 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 *  一般来说，删除节点可分为两个步骤：
 *      首先找到需要删除的节点；
 *      如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * Created by Myth-PC on 2019-10-05
 */
public class P450DeleteNodeInBST {
    // 难点：如何调整节点（要删除的节点与左边最大值leftMax或者右边的最小值rightMin,
    // 如果是叶子就交换，然后删除以前leftMax或者rightMin的节点，如果不是叶子叶子）
    // 删除rightMin右边最小值，使用迭代
    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return null;
        // 找到node
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = null, pre = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.val == key) break;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        // 寻找leftMax以及pre(left==null，寻找rightMin)
        if (cur.left != null) {
            TreeNode leftMax = cur.left;
            pre = cur;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }

        }
        return null;
        // rightMin赋值给node，删除rightMin位置
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val != key) {
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else {
                root.right = deleteNode(root.right, key);
            }
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 难点
            TreeNode rightMaxNode = findRightMax(root);  // 找到右侧最大值
            root.val = rightMaxNode.val;  // 与当前值交换
            rightMaxNode.val = key;
            root.right = deleteNode(root.right, key);  // 在右侧递归
        }
        return root;
    }
    private TreeNode findRightMax(TreeNode root) {
        TreeNode p = root.right;
        while (p != null && p.left != null) {
            p = p.left;
        }
        return p;
    }

}
