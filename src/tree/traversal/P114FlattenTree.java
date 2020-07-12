package tree.traversal;

import util.TreeNode;
 

/**
 * Title: 114. 二叉树展开为链表 
 * Desc: 给定一个二叉树，原地将它展开为一个单链表。 
 * Created by Myth-MBP on
 * 12/07/2020 in VSCode
 */
public class P114FlattenTree {
    TreeNode pre = null;
    // 前序  倒序 -->  右  左   root
    public void flatten(TreeNode root) {
        if (root ==  null) return;
        
        flatten(root.right);
        flatten(root.left);
        
        root.left = null;
        root.right = pre;
        pre = root;
    }
    
}