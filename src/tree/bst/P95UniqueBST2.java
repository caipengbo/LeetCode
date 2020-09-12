package tree.bst;

import util.*;
import java.util.*;
/**
* Title: 95. 不同的二叉搜索树 II
* Desc: 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
* Created by Myth-MBP on 31/08/2020 in VSCode
*/

public class P95UniqueBST2 {
    // 规律：列表的动态规划
    
    // 首先我们每次新增加的数字大于之前的所有数字，所以新增加的数字出现的位置只可能是:
    // 根节点或者是根节点的右孩子，右孩子的右孩子，右孩子的右孩子的右孩子等等，总之一定是右边。
    // 其次，新数字所在位置原来的子树，改为当前插入数字的左孩子即可，因为插入数字是最大的。

    // ==================对于下边的解 ,然后增加 3
    //           2
    //          /
    //         1
    
    // 1.把 3 放到根节点
    //      3
    //     /
    //    2
    //   /
    //  1

    // 2. 把 3 放到根节点的右孩子
    //      2
    //     / \
    //    1   3
    
    // ===================对于下边的解, 增加 3
    //      1
    //       \
    //        2

    // 1.把 3 放到根节点
    //        3
    //       /
    //      1
    //       \
    //        2
        
    // 2. 把 3 放到根节点的右孩子，原来的子树作为 3 的左孩子       
    //        1
    //         \
    //          3
    //         /
    //        2

    // 3. 把 3 放到根节点的右孩子的右孩子
    //    1
    //     \
    //      2
    //       \
    //        3
    // 以上就是根据 [ 1 2 ] 推出 [ 1 2 3 ] 的所有过程，可以写代码了。
    // 由于求当前的所有解只需要上一次的解，所有我们只需要两个 list，pre 保存上一次的所有解， cur 计算当前的所有解。


    public List<TreeNode> generateTrees(int n) {
        // 上一次的解
        List<TreeNode> pre = new ArrayList<TreeNode>();
        if (n == 0) {
            return pre;
        }
        pre.add(null);
        //每次增加一个数字
        for (int i = 1; i <= n; i++) {
            // 当前解
            List<TreeNode> cur = new ArrayList<TreeNode>();
            for (TreeNode root : pre) {  //遍历之前的所有解
                //插入到根节点
                TreeNode insert = new TreeNode(i);
                insert.left = root;
                cur.add(insert);
                //插入到右孩子，右孩子的右孩子...最多找 n 次孩子
                for (int j = 0; j <= n; j++) {
                    TreeNode root_copy = treeCopy(root); // 复制当前的树
                    TreeNode right = root_copy; //找到要插入右孩子的位置
                    int k = 0;
                    //遍历 j 次找右孩子
                    for (; k < j; k++) {
                        if (right == null)
                            break;
                        right = right.right;
                    }
                    //到达 null 提前结束
                    if (right == null)
                        break;
                    // 保存当前右孩子的位置的子树作为插入节点的左孩子
                    TreeNode rightTree = right.right;
                    insert = new TreeNode(i);
                    right.right = insert; //右孩子是插入的节点
                    insert.left = rightTree; //插入节点的左孩子更新为插入位置之前的子树
                    
                    cur.add(root_copy);// 加入结果中
                }
            }
            pre = cur;

        }
        return pre;
    }

    private TreeNode treeCopy(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = treeCopy(root.left);
        newRoot.right = treeCopy(root.right);
        return newRoot;
    }

}