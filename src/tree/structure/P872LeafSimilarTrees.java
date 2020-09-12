package tree.structure;

import util.TreeNode;

import java.util.LinkedList;
import java.util.List;


/**
* Title: 872. 叶子相似的树
* Desc: 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。 

如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。

* Created by Myth-MBP on 27/08/2020 in VSCode
*/

public class P872LeafSimilarTrees {
    private void allLeaf(TreeNode root, List<Integer> res) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        allLeaf(root.left, res);
        allLeaf(root.right, res);
    } 
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> res1 = new LinkedList<>();
        List<Integer> res2 = new LinkedList<>();
        allLeaf(root1, res1);
        allLeaf(root2, res2);
        return res1.equals(res2);
    }
}