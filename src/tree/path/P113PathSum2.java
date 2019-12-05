package tree.path;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P113PathSum2 {
    // 难点：如何保存结果，回溯法！！！
    private void findPath(TreeNode root, int sum, LinkedList<Integer> path, List<List<Integer>> res) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            // 注意必须new一个新的list
            if (root.val == sum) res.add(new LinkedList<>(path));
        }
        findPath(root.left, sum-root.val, path, res);
        findPath(root.right, sum-root.val, path, res);
        path.removeLast();
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        LinkedList path = new LinkedList();
        List<List<Integer>> res = new ArrayList<>();
        findPath(root, sum, path, res);
        return res;
    }
}