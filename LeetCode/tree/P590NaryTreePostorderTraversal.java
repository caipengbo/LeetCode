package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Title: 590. N叉树的后序遍历
 * Desc: https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * Created by Myth on 9/14/2019
 */
public class P590NaryTreePostorderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    // 迭代版本
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> ret = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        stack.add(cur);
        while (!stack.empty()) {
            cur = stack.pop();
            if (cur != null) {
                ret.addFirst(cur.val);
                for (Node node : cur.children) {
                    stack.add(node);
                }
            }
        }
        return ret;
    }
}
