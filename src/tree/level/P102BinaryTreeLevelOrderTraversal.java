package tree.level;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Title: 102. 二叉树的层次遍历
 * Desc: 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地, 每一层都放到一个链表内，从左到右访问所有节点）。
 * 测试用例：[] [1] [1,2]
 * Tag： 深度优先遍历（递归写法） 和 广度优先遍历（迭代写法）
 * Created by Myth on 9/20/2019
 */
public class P102BinaryTreeLevelOrderTraversal {
    // 非递归写法
    // 个人写法：使用了两个队列，每次要交换两个队列，逻辑以及代码比较复杂
    public List<List<Integer>> levelOrderMyself(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        List<Integer> levelValue = new LinkedList<>();
        Queue<TreeNode> curLevelQueue = new LinkedList<>();
        Queue<TreeNode> nextLevelQueue = new LinkedList<>();
        nextLevelQueue.add(root);
        levelValue.add(root.val);
        while (!nextLevelQueue.isEmpty()) {
            // 交换两个队列
            Queue<TreeNode> temp = curLevelQueue;
            curLevelQueue = nextLevelQueue;
            nextLevelQueue = temp;
            ret.add(levelValue);
            levelValue = new LinkedList<>();
            while (!curLevelQueue.isEmpty()) {
                TreeNode node = curLevelQueue.poll();
                if (node.left != null) {
                    nextLevelQueue.add(node.left);
                    levelValue.add(node.left.val);
                }
                if (node.right != null) {
                    nextLevelQueue.add(node.right);
                    levelValue.add(node.right.val);
                }
            }
        }
        return ret;
    }
    // 参考别人的代码：使用一个队列，下一层元素数目等于当前队列中元素的数目
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
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
            ret.add(level);
        }
        return ret;
    }
    // 递归写法(深度优先遍历)，参数包括(node, level, resList)，然后将第 K 层放到第K个 List
    // 重点：什么时候初始化List
    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;
        // Key
        if (level >= res.size()) res.add(new LinkedList<>());
        res.get(level).add(root.val);
        helper(root.left, level+1, res);
        helper(root.right, level+1, res);
    }
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        helper(root, 0, res);
        return res;
    }
}
