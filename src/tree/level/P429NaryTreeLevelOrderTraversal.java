package tree.level;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Title:
 * Desc:
 * Created by Myth on 9/20/2019
 */
public class P429NaryTreeLevelOrderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    // Recursive
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int nodeCount = queue.size();
            List<Integer> level = new ArrayList<>(nodeCount);
            for (int i = 0; i < nodeCount; i++) {
                Node node = queue.poll();
                level.add(node.val);
                for(Node child : node.children) {
                    if (child != null) queue.add(child);
                }
            }
            ret.add(level);
        }
        return ret;
    }
    // iterative
    private void helper(Node root, int level, List<List<Integer>> res) {
        if (root == null) return;
        if (level >= res.size()) res.add(new LinkedList<>());
        res.get(level).add(root.val);
        for(Node child : root.children) {
            helper(child, level + 1, res);
        }
    }
    public List<List<Integer>> levelOrderIter(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        helper(root, 0, res);
        return res;
    }
}
