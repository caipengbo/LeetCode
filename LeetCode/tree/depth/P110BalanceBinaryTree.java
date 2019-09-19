package tree.depth;

import util.TreeNode;

/**
 * Title: 110. 平衡二叉树
 * Desc: 判断树是否是高度平衡的二叉树（左右子树的高度不相差1）
 * Created by Myth-PC on 2019-09-18
 */
public class P110BalanceBinaryTree {
    // 在104的思路基础上修改：先计算树的高度，如果发现当前的节点高度差大于1了
    // 那么就直接返回-1
    private int recursive(TreeNode root) {
        if (root == null) return 0;
        int left = recursive(root.left);
        if (left == -1) return -1;
        int right = recursive(root.right);
        if (right == -1) return -1;
        if (Math.abs(left-right)>1) return -1;
        return Math.max(left, right) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        return recursive(root) != -1;
    }
}
