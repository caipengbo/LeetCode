package tree.structure;

import util.TreeNode;

import java.util.LinkedList;
import java.util.List;

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