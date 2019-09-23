class P113PathSum2 {
    // 难点：如何保存结果，回溯法！！！
    private boolean path(TreeNode root, int sum, List<Integer> res) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return  root.val == sum;
        }
        res.add(root.val);   
        path(root.left, sum-root.val, res) || hasPathSum (root.right, sum-root.val); 
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        
    }
}