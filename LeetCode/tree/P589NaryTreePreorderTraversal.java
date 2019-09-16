package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Title: 589. N叉树的前序遍历
 * Desc:
 * Created by Myth on 9/14/2019
 */
public class P589NaryTreePreorderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public void preorder(Node root, List<Integer> ret) {
        if (root == null) return;
        ret.add(root.val);
        for(Node child : root.children) {
            preorder(child, ret);
        }
    }
    public List<Integer> preorder(Node root) {
        List<Integer> ret = new ArrayList<>();
        preorder(root, ret);
        return ret;
    }
    public List<Integer> preorderInterative(Node root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return null;
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        stack.add(cur);
        while (!stack.empty()) {
            cur = stack.pop();
            ret.add(cur.val);
            for (int i = cur.children.size() - 1; i >= 0; i--) {
                stack.add(cur.children.get(i));
            }
        }
        return ret;
    }
}
