package tree.bst;

import util.TreeNode;
import util.TreeUtil;

/**
 * Title: 99. 恢复二叉搜索树
 * Desc: 二叉搜索树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * Created by Myth-PC on 2019-10-04
 */
public class P99RecoverBST {
    // 难点：找到这两个错误的节点
    // 有序的序列，交换两个元素，会导致增长序列出现两个(或者一个)下降点
    // 两个下降点: first是第一个下降点处较大的元素；second是第二个下降点处较小的元素
    // 一个下降点: first下降点处较大元素；second是下降点处较小元素
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode pre;
    public void recoverTree(TreeNode root) {
        first = null;
        second = null;
        pre = null;
        traverse(root);
        // 交换 first 和 second
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        if (pre != null && pre.val > root.val) {
            // 此处是重点
            if (first == null) {
                first = pre;
                second = root;  // 注意此处（只有一个下降点时）
            } else second = root;
        }
        System.out.println(root.val);
        pre = root;
        traverse(root.right);
    }

    public static void main(String[] args) {
        P99RecoverBST p99 = new P99RecoverBST();
        TreeNode root = TreeUtil.stringToTreeNode("[3,1,4,null,null,2]");
        p99.recoverTree(root);
        TreeUtil.prettyPrintTree(root);
    }
}
