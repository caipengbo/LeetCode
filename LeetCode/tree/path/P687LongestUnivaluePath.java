package tree.path;

import util.TreeNode;
import util.TreeUtil;

/**
 * Title: 687. 最长同值路径（本题是任意两个节点的路径）
 * Desc: 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。
 * 注意：这条路径可以经过也可以不经过根节点。两个节点之间的路径长度由它们之间的边数表示。
 * Created by Myth on 9/24/2019
 */
public class P687LongestUnivaluePath {
    // 这种路径是 1.左子树最长同值路径（单箭头路径）  2. 右子树最长同值路径（单箭头路径） 的 最大值
    private int longest = 0;
    public int longestUnivaluePath(TreeNode root) {
        longest = 0;
        arrowPath(root);
        return longest;
    }
    private int arrowPath(TreeNode root) {
        if (root == null) return 0;
        int left = arrowPath(root.left);
        int right = arrowPath(root.right);
        int arrowLeft = 0, arrowRight = 0;
        if (root.left != null && root.left.val == root.val) arrowLeft = left + 1;
        if (root.right != null && root.right.val == root.val) arrowRight = right + 1;
        // 更新最终结果是双向的
        longest = Math.max(longest, arrowLeft + arrowRight);
        // 返回的是单向的
        return Math.max(arrowLeft, arrowRight);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtil.stringToTreeNode("[1,4,5,4,4,5]");
        P687LongestUnivaluePath p687 = new P687LongestUnivaluePath();
        System.out.println(p687.longestUnivaluePath(root));
    }
}
