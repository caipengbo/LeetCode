public class P814BinaryTreePruning {
    // 判断是否含有1
    private boolean hasOne(TreeNode root) {
        if (root == null) return false;
        if (root.val == 1) return true;
        return hasOne(root.left) || hasOne(root.right);
    }
    public TreeNode pruneTree(TreeNode root) {
        if (!hasOne(root)) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return root;
    }
    // 后续遍历写法
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null)
            return null;
        return root;
    }
    
}