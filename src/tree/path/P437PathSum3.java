package tree.path;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 437. 路径总和3
 * Desc: 注意：路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * Created by Myth on 9/24/2019
 */
public class P437PathSum3 {
    //  本人做法：回溯+DFS：时间复杂度很高，因为有大量的重复运算
    private int count = 0;
    private void findPath(TreeNode root, int sum) {
        if (root == null) return;
        if (root.val == sum) count++;
        findPath(root.left, sum-root.val);
        findPath(root.right, sum-root.val);
    }
    private void findAllNode(TreeNode root, int sum) {
        if (root == null) return;
        findPath(root, sum);
        findAllNode(root.left, sum);
        findAllNode(root.right, sum);
    }
    public int pathSumPrivate(TreeNode root, int sum) {
        findAllNode(root, sum);
        return count;
    }
    // 优化算法：（使用 HashMap）保存前缀和（从根到当前节点的和）
    // 任意路径长度 = 前缀和的差
    public int pathSum(TreeNode root, int sum) {
        // <preSum, count>
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        helper(root, 0, sum, preSum);
        return count;
    }
    private void helper(TreeNode root, int curSum, int sum, HashMap<Integer, Integer> preSum) {
        if (root == null) return;
        curSum += root.val;
        count += preSum.getOrDefault(curSum - sum, 0);
        preSum.put(curSum, preSum.getOrDefault(curSum,0) + 1);
        helper(root.left, curSum, sum, preSum);
        helper(root.right, curSum, sum, preSum);
        preSum.put(curSum, preSum.get(curSum)-1);
    }
}
