package tree.path;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 257. 二叉树的所有路径
 * Desc: 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * Created by Myth on 9/29/2019
 */
public class P257BinaryTreeAllPaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        addPath(root, "", res);
        return res;
    }
    private void addPath(TreeNode root, String curPath, List<String> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            curPath += Integer.toString(root.val);
            res.add(curPath);
            return;
        }
        curPath += Integer.toString(root.val) + "->";
        addPath(root.left,  curPath, res);
        addPath(root.right,  curPath, res);
    }
}