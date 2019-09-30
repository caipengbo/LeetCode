package tree.path;

import util.TreeNode;

/**
 * Title: 236. 二叉树的最近公共祖先
 * Desc: 本题比235稍微难一些，235题可以通过数值的大小判断左右子树，该题不是BST不行
 * Created by Myth on 9/30/2019
 */
public class P236LowestCommonAncestorOfBinaryTree {
    // 递归1：对每个节点对应的子树，若该子树不含有p或q，返回nullptr；
    // 否则，如果p和q分别位于当前子树根节点两侧，则返回当前节点，
    // 否则（p和q在同一侧，或者只有某一侧有p或q）返回来自左边或右边的LCA。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
    }

}
