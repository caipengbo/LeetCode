package tree.depth;

import java.util.List;

/**
 * Title: 559. N叉树的最大深度
 * Desc: 给定一个 N 叉树，找到其最大深度。最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * Created by Myth on 9/19/2019
 */
public class P559MaxDepthNnaryTree {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int max = 0, cur;
        for (Node node : root.children) {
            cur = maxDepth(node);
            if (max < cur) max = cur;
        }
        return max+1;
    }
}
