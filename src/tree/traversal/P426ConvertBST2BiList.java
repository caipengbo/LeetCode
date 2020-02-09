package tree.traversal;

import util.TreeNode;

/**
 * Title: 426. 将二叉搜索树转化为排序的双向链表 
 * Desc: 剑指offer 36题 
 * Created by Myth-PC on 09/02/2020 in VSCode
 */
public class P426ConvertBST2BiList {
    TreeNode pre = null;
    public TreeNode convert(TreeNode root) {
        if (root == null) return null;
        this.pre = null;
        convertHelper(root);
        TreeNode p = root;
        while(p.left != null) p = p.left;
        return p;
    }
    // 中序遍历 记录前一个访问的节点
    // 使用全局变量保存pre，在convertHelper中传pre的值不行
    public void convertHelper(TreeNode cur) {
        if (cur == null) return;
        convertHelper(cur.left);
    
        cur.left = this.pre;
        if (pre != null) this.pre.right = cur;
        this.pre = cur;
    
        convertHelper(cur.right);
    }
}