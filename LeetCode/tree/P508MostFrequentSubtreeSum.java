package tree;

import util.TreeNode;

import java.util.*;

/**
 * Title: 508. 出现次数最多的子树元素和
 * Desc: 给出二叉树的根，找出出现次数最多的子树元素和。
 * 一个结点的子树元素和定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * 然后求出出现次数最多的子树元素和。
 * 如果有多个元素出现的次数相同，返回所有出现次数最多的元素（不限顺序）
 *
 * Created by Myth-PC on 2019-10-02
 */
public class P508MostFrequentSubtreeSum {
    // 后续遍历 + TreeMap
    // 如何每个sum出现的次数（按照次数排序）
    public int[] findFrequentTreeSum(TreeNode root) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        subTreeSum(root, map);
        // 最大次数
        int maxCount = 0;
        for (Integer key : map.keySet()) {
            maxCount = Math.max(maxCount, map.get(key));
        }
        ArrayList<Integer> ret = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if (map.get(key) == maxCount) ret.add(key);
        }
        int[] arr = new int[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            arr[i] = ret.get(i);
        }
        return arr;
    }
    // 不用保存每个TreeNode Key的和（因为是后序遍历，遍历一遍自底向上所有的值就都求一次了）
    private int subTreeSumError(TreeNode root, TreeMap<TreeNode, Integer> treeSum) {
        if (root == null) return 0;
        if (treeSum.containsKey(root)) return treeSum.get(root);
        int left = subTreeSumError(root.left, treeSum);
        int right = subTreeSumError(root.right, treeSum);
        treeSum.put(root, left+right+root.val);
        return left+right+root.val;
    }
    private int subTreeSum(TreeNode root, TreeMap<Integer, Integer> map) {
        if (root == null) return 0;
        int left = subTreeSum(root.left, map);
        int right = subTreeSum(root.right, map);
        int sum = left+right+root.val;
        map.put(sum, map.getOrDefault(sum, 0)+1);
        return sum;
    }

}
