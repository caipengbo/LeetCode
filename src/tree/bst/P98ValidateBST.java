package tree.bst;

import util.TreeNode;

/**
 * Title: 98.验证二叉搜索树
 * Desc:
 *      Error 1. 只验证了val > left && val < right
 *            2. 不知道如何储存leftMax 和 rightMin
 * Created by Myth-PC on 2019-10-02
 */
public class P98ValidateBST {
    // 个人错误版本
    // 注意 cur要 大于左边最大值，小于右边的最小值
    private int leftMax = Integer.MIN_VALUE;
    private int rightMin = Integer.MAX_VALUE;
    // 错误版本
    public boolean isValidBSTError(TreeNode root) {
        if (root == null) return true;
        if (!isValidBSTError(root.left) || !isValidBSTError(root.right)) return false;
        if (root.left == null) return root.val < rightMin;
        if (root.right == null) return  root.val > leftMax;
        leftMax = Math.max(leftMax, root.left.val);
        rightMin = Math.min(rightMin, root.right.val);
        return (root.val > leftMax && root.val < rightMin);
    }
    //=====================================================================
    // 正确版本 思路1
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    // BST隐藏着一个上下界
    private boolean helper(TreeNode root, Integer low, Integer high) {
        if (root == null) return true;
        if (low != null && root.val <= low) return false;
        if (high != null && root.val >= high) return false;
        if (!helper(root.left, low, root.val)) return false;
        if (!helper(root.right, root.val, high)) return false;
        return true;
    }
    // 思路2(中序遍历的非递归写法见530题)：BST的中序遍历序列是一个顺序序列
    // private int pre = Integer.MIN_VALUE;  // 只保存值，无法判断是不是第一次赋值（如果树只有一个节点，并且节点值为MIN_VALUE）
    private TreeNode pre = null;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST2(root.left)) return false;
        if (pre != null && pre.val >= root.val) return false;
        pre = root;
        return isValidBST2(root.right);
    }

}
