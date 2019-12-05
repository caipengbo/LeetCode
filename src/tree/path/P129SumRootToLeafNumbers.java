package tree.path;

import util.TreeNode;

/**
 * Title: 129. 求根到叶子节点数字之和
 * Desc:
 * Created by Myth on 9/25/2019
 */
public class P129SumRootToLeafNumbers {
    private int sum;
    public int sumNumbers(TreeNode root) {
        sum = 0;
        helper(root, 0);
        return sum;
    }
    private void helper(TreeNode root, int pre) {
        if (root == null) return;
        pre = pre * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += pre;
            return;
        }
        helper(root.left, pre);
        helper(root.right, pre);
    }
}
