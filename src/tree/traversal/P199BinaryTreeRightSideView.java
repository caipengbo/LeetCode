package tree.traversal;

import java.util.*;

import util.TreeNode;

/**
* Title: 199. 二叉树的 右视图
* Desc: 从右看，能看到的序列
* Created by Myth-MBP on 13/07/2020 in VSCode
*/
public class P199BinaryTreeRightSideView {
    // 1. BFS : 每一层最右边的元素

    // 2. DFS: 前序遍历，先访问右子树，然后再访问左子树

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, 0, ret);
        return ret;
    }
    private void dfs(TreeNode root, int level, List<Integer> ret) {
        if (root == null) {
            return;
        }
        if (level == ret.size()){
            ret.add(root.val);
        }
        level++;
        dfs(root.right, level, ret);
        dfs(root.left, level, ret);
    }
}