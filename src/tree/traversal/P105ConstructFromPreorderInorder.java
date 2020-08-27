package tree.traversal;

import util.TreeNode;

/**
 * Title:  105. 从前序与中序遍历序列构造二叉树
 * Desc: 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 *      你可以假设树中没有重复的元素。
 * Created by Myth on 9/17/2019
 */
public class P105ConstructFromPreorderInorder {
    // 难点：如何在数组上分区域
    // 方案：递归的在子数组上进行操作
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length-1, preorder, inorder);
    }
    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart >= preorder.length || inStart > inEnd) return null;
        // 当前根节点
        TreeNode root = new TreeNode(preorder[preStart]);
        // 中序遍历中找到当前的 根节点，划分左右区域(为了加速可以使用 HashMap，O(1)时间找到inIndex)
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }
        // 划分区域
        root.left = helper(preStart+1, inStart, inIndex-1, preorder, inorder);
        root.right = helper(preStart+inIndex-inStart+1, inIndex+1, inEnd, preorder, inorder);
        return root;
    }
}
