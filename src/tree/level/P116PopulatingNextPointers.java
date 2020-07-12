package tree.level;


/**
* Title: 116. 填充每个节点的下一个右侧节点指针（满二叉树）
* Desc: 填充每一层的next指针，不使用额外的空间
* Created by Myth-MBP on 12/07/2020 in VSCode
*/

public class P116PopulatingNextPointers {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    // 记录每一层的head指针，使用head.next在当前层横移
    // 由当前层为下一层next指针赋值, 有两类next，一个是同父的，一个是跨父的
    //  每个 node 左子树的 next , 就是 node 的右子树
    //  每个 node 右子树的 next, 就是 node next 的 左子树
    
    // 递归版本
    private void dfs(Node node, Node next) {
        if(node != null) {
            node.next = next;
            dfs(node.left, node.right);
            dfs(node.right, node.next != null ? node.next.left : null);
        }
    }
    // 迭代版本
    public Node connect2(Node root) {
        
        if (root == null) {
            return root;
        }
        // 每一层的最左节点
        Node leftmost = root;
        
        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                // CONNECTION 1
                head.left.next = head.right;
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            
            leftmost = leftmost.left;
        }
        
        return root;
    }
}