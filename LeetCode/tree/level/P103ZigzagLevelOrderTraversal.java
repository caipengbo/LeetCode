package tree.level;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Title: 103. 二叉树的锯齿形层次遍历
 * Desc: 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
 * Created by Myth on 9/20/2019
 */
public class P103ZigzagLevelOrderTraversal {
    // 可以用翻转, 使用flag做标记
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = false;  // 是否翻转
        queue.add(root);
        while (!queue.isEmpty()) {
            int nodeCount = queue.size();  // Key
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < nodeCount; i++) {
                TreeNode node = queue.poll();
                if (!flag) level.add(node.val);
                else level.addFirst(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ret.add(level);
            flag = !flag;
        }
        return ret;
    }
    // 递归：类似于102题的思路，奇数时候头部插入结果List，偶数时候尾部插入
    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;
        // Key
        if (level >= res.size()) res.add(new LinkedList<>());
        if(level % 2 == 0) res.get(level).add(root.val);
        else ((LinkedList)res.get(level)).addFirst(root.val);
        helper(root.left, level+1, res);
        helper(root.right, level+1, res);
    }
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        helper(root, 0, res);
        return res;
    }
}
