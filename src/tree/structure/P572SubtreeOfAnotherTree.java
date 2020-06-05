package tree.structure;

import util.TreeNode;

/**
 * Title: 572.另一个树的子树
 * Desc: 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * Created by Myth on 9/19/2019
 */
public class P572SubtreeOfAnotherTree {
    // 子树：相同的结构和结点值（不是结点一致哦）
    // 递归检查两个树是否一致
    private boolean compTree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return s == t;
        boolean left = false, right = false;
        if (s.val == t.val) {
            left = compTree(s.left, t.left);
            right = compTree(s.right, t.right);
        }
        return left && right;
    }
    // 个人写法
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        boolean same = false;
        if (s.val == t.val) {
            same = compTree(s, t);
            if (same) return true;
        }
        same = same || isSubtree(s.left, t);
        if (same) return true;
        same = same || isSubtree(s.right, t);
        return same;
    }
    // 优化写法
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        // if (s == null) return false;
        boolean same = false;
        if (s.val == t.val) same = compTree(s, t);
        if (!same) same = isSubtree(s.left, t);
        if (!same) same = isSubtree(s.right, t);
        return same;
    }
    // 2020/6/5
    public boolean isSubtree3(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) {
            return false;
        }
        if (s.val == t.val && isSame(s, t)) {
            return true;
        }
        return isSubtree3(s.left, t) || isSubtree3(s.right, t);
    }
    private boolean isSame(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null || tree2 == null) {
            return tree1 == tree2;
        }
        return tree1.val == tree2.val && isSame(tree1.left, tree2.left) && isSame(tree1.right, tree2.right);
    }
}
