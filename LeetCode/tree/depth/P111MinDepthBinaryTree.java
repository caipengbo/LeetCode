package tree.depth;

import util.TreeNode;

/**
 * Title: 111. 二叉树的最小深度
 * Desc:
 * Created by Myth-PC on 2019-09-18
 */
public class P111MinDepthBinaryTree {
    // 难点：当二叉树退化成单侧时
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 下面两句是和Max Depth不一样的地方
        if (root.left == null) return right+1;
        if (root.right == null) return left+1;
        return Math.min(left, right)+1;

    }

}
