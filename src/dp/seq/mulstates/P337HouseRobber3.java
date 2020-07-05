package dp.seq.mulstates;

import java.util.HashMap;

import util.TreeNode;

/**
 * Title: 337. 打家劫舍3 
 * Desc: 二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * Created by Myth on 01/03/2020 in VSCode
 */

public class P337HouseRobber3 {
    
    // DFS
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }
        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }
        // 选择当前与不选当前
        return Math.max(money, rob(root.left) + rob(root.right));
    }
    // 记忆化搜索
    public int rob2(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternal(root, memo);
    }
    
    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;
    
        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }
    // DFS + DP 
    public int rob3(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }
    // 最大钱数的两种状态： 0不偷时能获得的最大钱数，1偷时能获得的最大钱数
    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];
    
        // 递归
        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);
    
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;
    
        return result;
    }

}