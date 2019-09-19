package tree.structure;

import util.TreeNode;

/**
 * Title: 100. 相同的树
 * Desc: 给定两个二叉树，编写一个函数来检验它们是否相同。如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * Created by Myth on 9/18/2019
 */
public class P100SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q != null && p.val != q.val) return false;
        if ((p != null && q == null) || (p == null && q != null)) return false;
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
    // 简化判断条件
    public boolean isSameTree2(TreeNode p, TreeNode q) {
//        if (p == null && q == null) return true;  // 同时为空
//        if (p == null || q == null) return false;  // 不同时为空
        if (p == null || q == null) return p == q;  // 更精简的写法
        if (p.val != q.val) return false;  // 都不为空
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
