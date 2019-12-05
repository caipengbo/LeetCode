package tree.bst;

import util.TreeNode;

/**
 * Title: 108. 将有序数组转换为二叉搜索树
 * Desc: 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * Created by Myth-PC on 2019-10-05
 */
public class P108ConvertSortedArrayToBalancedBST {
    // 树尽量平衡 —— 要求数组尽量划分均等(二分)（使用下标进行划分，注意数目的奇偶）
    // 0 1 2 3 4 5
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = helper(nums, 0, nums.length-1);
        return root;
    }
    private TreeNode helper(int[] nums, int i, int j) {
        if (i < 0 || j >= nums.length ||  i > j) return null;
        int mid = (i+j)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, i, mid-1);
        node.right = helper(nums, mid+1, j);
        return node;
    }

}
