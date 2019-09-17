package tree;

import util.TreeNode;

/**
 * Title: 106. 从中序与后序遍历序列构造二叉树
 * Desc: 在105的基础（思路）之上修改
 * Created by Myth on 9/17/2019
 */
public class P106ConstructFromInorderPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length-1, 0, inorder.length-1, postorder, inorder);
    }
    public TreeNode helper(int postStart, int inStart, int inEnd, int[] postorder, int[] inorder) {
        if (postStart < 0 || inStart > inEnd) return null;
        // 当前根节点
        TreeNode root = new TreeNode(postorder[postStart]);
        // 后序遍历中从后往前找到当前根节点，划分左右区域(为了加速可以使用 HashMap，O(1)时间找到inIndex)
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }
        // 划分区域(难点)
        root.left = helper(postStart-(inEnd-inIndex+1), inStart, inIndex-1, postorder, inorder);
        root.right = helper(postStart-1, inIndex+1, inEnd, postorder, inorder);
        return root;
    }
}
