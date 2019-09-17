package tree;

import util.TreeNode;

/**
 * Title:  889. 根据前序和后序遍历构造二叉树
 * Desc: 返回与给定的前序和后序遍历匹配的任何二叉树。pre 和 post 遍历中的值是不同的正整数。
 * Created by Myth on 9/17/2019
 */
public class P889ConstructFromPreorderPostorder {
    int preIndex = 0, posIndex = 0;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (root.val != post[posIndex])
            root.left = constructFromPrePost(pre, post);
        if (root.val != post[posIndex])
            root.right = constructFromPrePost(pre, post);
        posIndex++;
        return root;
    }

}
