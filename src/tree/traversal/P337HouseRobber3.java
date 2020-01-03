package tree.traversal;

import java.util.HashMap;
import java.util.Map;

import util.TreeNode;

/**
 * Title: 337. 打家劫舍3 
 * Desc: 
 * Created by Myth on 01/03/2020 in VSCode
 */

public class P337HouseRobber3 {
    // 普通DFS
    public int rob(TreeNode root) {
        if (root == null) return 0;
        return Math.max(dfs(false, root.left)+dfs(false, root.right), dfs(true, root.left)+dfs(true, root.right)+root.val);
    }
    public int dfs(boolean pri, TreeNode root) {
        if (root == null) {
            return 0;   
        }
       
        int sum = 0;
        if (pri) {
            sum = dfs(false, root.left) + dfs(false, root.right);
        } else {
            sum = Math.max(dfs(false, root.left)+dfs(false, root.right), dfs(true, root.left)+dfs(true, root.right)+root.val);
        }
        return sum;
    }
    // 记忆化
    Map<TreeNode, Integer> mem0;
    Map<TreeNode, Integer> mem1;
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        mem0 = new HashMap<>();
        mem1 = new HashMap<>();
        return Math.max(dfs2(false, root.left)+dfs2(false, root.right), dfs2(true, root.left)+dfs2(true, root.right)+root.val);
    }
    public int dfs2(boolean pri,TreeNode root) {
        if (root == null) {
            return 0;   
        }
        int sum = 0;
        if (pri) {
            if (mem1.containsKey(root)) return mem1.get(root);
            sum = dfs(false, root.left) + dfs(false, root.right);
            mem1.put(root, sum);
        } else {
            if (mem0.containsKey(root)) return mem0.get(root);
            sum = Math.max(dfs2(false, root.left)+dfs2(false, root.right), dfs2(true, root.left)+dfs2(true, root.right)+root.val);
            mem0.put(root, sum);
        }
        return sum;
    }
// ================================================================================
    // 精简代码
    public int rob3(TreeNode root) {
        if (root == null) return 0;
    
        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }
    
        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }
        // 选择当前与不选当前
        return Math.max(money, rob3(root.left) + rob3(root.right));
    }
    // 记忆化搜索
    public int rob4(TreeNode root) {
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

}