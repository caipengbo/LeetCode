package tree.bst;

import util.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 501. 二叉搜索树的所有众数
 * Desc: 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *      结点左子树中所含结点的值小于等于当前结点的值
 *      结点右子树中所含结点的值大于等于当前结点的值
 *      左子树和右子树都是二叉搜索树
 *
 * Created by Myth-PC on 2019-10-05
 */
public class P501FindModeInBST {
    private int maxCount;
    private int count;
    private TreeNode pre = null;

    // 个人思路：中序遍历一遍Tree找到出现次数最多的元素个数，然后再中序遍历找到count==maxcount的元素
    // 参考思路：只中序遍历一次，然后往list里面添加元素，如果更新maxCount，那么清空list重新添加
    public int[] findMode(TreeNode root) {
        maxCount = 0;
        count = 1;
        pre = null;
        List<Integer> res = new ArrayList<>();
        find(root);
        maxCount = Math.max(maxCount, count);
        System.out.println(maxCount);
        count = 1;
        pre = null;
        find(root, res);
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
    private void find(TreeNode root) {
        if (root == null) return;
        find(root.left);
        if (pre != null) {
            if (pre.val == root.val) count++;
            else {
                maxCount = Math.max(maxCount, count);
                count = 1;
            }
        }
        pre = root;
        find(root.right);
    }

    private void find(TreeNode root, List<Integer> res) {
        if (root == null) return;
        find(root.left, res);
        if (maxCount == 1) {
            res.add(root.val);
        } else {
            if (pre != null) {
                if (pre.val == root.val) count++;
                else count = 1;
                if (count == maxCount) res.add(pre.val);
            }
        }
        pre = root;
        find(root.right, res);
    }
    // =============================
    public int[] findMode2(TreeNode root) {
        maxCount = 0;
        count = 1;
        pre = null;
        List<Integer> res = new ArrayList<>();
        find2(root, res);
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
    private void find2(TreeNode root, List<Integer> res) {
        if (root == null) return;
        find2(root.left, res);
        if (pre != null && pre.val == root.val) count++;
        else count = 1;

        if (count == maxCount) {
            res.add(root.val);
        } else if (count > maxCount) {
            maxCount = count;
            res.clear();
            res.add(root.val);
        }
        pre = root;
        find2(root.right, res);
    }

    public static void main(String[] args) {
        P501FindModeInBST p501 = new P501FindModeInBST();
//        TreeNode root = TreeUtil.stringToTreeNode("[3,1,7,1,1,6]");
//        TreeNode root = TreeUtil.stringToTreeNode("[1,null,2,2]");
//        TreeNode root = TreeUtil.stringToTreeNode("[1]");
//        TreeNode root = TreeUtil.stringToTreeNode("[1,null,2]");
        TreeNode root = TreeUtil.stringToTreeNode("[3,2,3,null,null,3,4,null,null,4,5,null,null,5,6]");
        int[] ret = p501.findMode2(root);
        System.out.println(Arrays.toString(ret));
    }
}
