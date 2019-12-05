package tree.serialize;

import util.TreeNode;
import util.TreeUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Title: 297.二叉树的序列化与反序列化
 * Desc: LeetCode官方使用的是层次遍历，本题使用DFS
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 * Created by Myth-PC on 2019-10-01
 */
public class P297SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> ret = new LinkedList<>();
        rserialize(root, ret);
        String s = String.join(",", ret);
        return s;
    }
    private void rserialize(TreeNode root, List<String> ret) {
        if (root == null) {
            ret.add("null");
            return;
        }
        ret.add(Integer.toString(root.val));
        rserialize(root.left, ret);
        rserialize(root.right, ret);
    }

    // Decodes your encoded data to tree.
    // 注意此处使用了类的成员变量(不符合要求)，修改方法是使用Queue，每次使用第一个元素，就不用记录index了
    private int index = 0;
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        index = 0;
        return rdeserialize(vals);
    }

    private TreeNode rdeserialize(String[] vals) {
        if (index >= vals.length || "null".equals(vals[index])) return null;
        TreeNode root = new TreeNode(Integer.parseInt(vals[index]));
        ++index;
        root.left = rdeserialize(vals);
        ++index;
        root.right = rdeserialize(vals);
        return root;
    }

    public static void main(String[] args) {
        P297SerializeAndDeserializeBinaryTree p297 = new P297SerializeAndDeserializeBinaryTree();
        TreeNode root = TreeUtil.stringToTreeNode("[1,2,3,4,5]");
        String s = p297.serialize(root);
        System.out.println(s);
        TreeNode node = p297.deserialize(s);
        TreeUtil.prettyPrintTree(node);
    }
}
