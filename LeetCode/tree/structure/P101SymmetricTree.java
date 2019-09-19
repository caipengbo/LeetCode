package tree.structure;

import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Title: 101. 对称二叉树（镜像对称）
 * Desc: 给定一个二叉树，检查它是否是镜像对称的。例如，二叉树 [1,2,2,3,4,4,3]是对称的
 * Created by Myth on 9/18/2019
 */
public class P101SymmetricTree {
    // 递归写法
    private boolean recursive(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null || tree2 == null) return tree1 == tree2;
        if (tree1.val != tree2.val) return false;
        return recursive(tree1.left, tree2.right) && recursive(tree1.right, tree2.left);
    }
    // 迭代写法（比较两个树是否是对称的）
    // 使用队列，BFS, 将两个树都加入队列
    private boolean iterative(TreeNode tree1, TreeNode tree2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree1);
        queue.add(tree2);
        TreeNode node1, node2;
        while (!queue.isEmpty()) {
            node1 = queue.poll();
            node2 = queue.poll();
            if (node1 == null || node2 == null) {
                if (node1 != node2) return false;
            } else {
                if (node1.val != node2.val) return false;
                queue.add(node1.left);
                queue.add(node2.right);
                queue.add(node1.right);
                queue.add(node2.left);
            }
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
//        if (root == null) return true;
        return iterative(root, root);
    }
}
