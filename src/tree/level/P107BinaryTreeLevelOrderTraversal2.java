package tree.level;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Title: 107. 二叉树的层次遍历 II
 * Desc: 和102题一模一样，只不过本题需要将结果翻转一下，使用链表的头插，实现结果的翻转
 * Created by Myth on 9/20/2019
 */
public class P107BinaryTreeLevelOrderTraversal2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ret = new LinkedList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int nodeCount = queue.size();  // Key
            List<Integer> level = new ArrayList<>(nodeCount);
            for (int i = 0; i < nodeCount; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ret.addFirst(level);
        }
        return ret;
    }
}
