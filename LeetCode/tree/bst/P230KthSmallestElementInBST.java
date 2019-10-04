package tree.bst;

import util.TreeNode;

/**
 * Title: 230. 二叉搜索树中第K小的元素
 * Desc:
 * Created by Myth-PC on 2019-10-04
 */
public class P230KthSmallestElementInBST {
    // BST的重要性质是中序遍历有序，所以计数等于K时候退出
    private int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        TreeNode kth = find(root, k);
        if (kth != null) return kth.val;
        return -1;
    }
    private TreeNode find(TreeNode root, int k) {
        if (root == null) return null;
        TreeNode left = find(root.left, k);
        if (left != null) return left;
        count++;
        if (count == k) return root;
        return find(root.right, k);
    }
    // 改良后的写法
    private int count2 = 0;
    private int ret = 0;
    public int kthSmallest2(TreeNode root, int k) {
        count2 = 0;
        ret = -1;
        find(root, k);
        return ret;
    }
    private void find2(TreeNode root, int k) {
        if (root.left != null) find(root.left, k);
        count++;
        if (count == k) {
            ret = root.val;
            return;
        }
        if (root.right != null) find(root.right, k);
    }

}
