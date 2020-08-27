package list.split;

/**
 * Title: 138. 复制带随机指针的链表
 * Desc: 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。要求返回这个链表的深拷贝。
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 * 难点：如何拷贝 随机节点？
 *      思路1：使用 HashMap: 需要额外的空间
 *      2. 旧节点和新节点 交错排列，然后复制 random，再拆开 next
 * Created by Myth on 9/12/2019
 */

public class P138RandomPointerListCopy {
    private class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    // 拆开链表的地方有点不一样
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node p = head, newNode, q;
        // 交叉排列
        while (p != null) {
            newNode = new Node(p.val, null, null);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }
        // 赋值新节点的 random
        p = head;
        while (p != null) {
            p.next.random = (p.random != null ? p.random.next : null);
            p = p.next.next;
        }
        // 拆开链表（拆解方法 2）
        p = head;
        q = head.next;
        newNode = p.next;
        while (p.next != null && p.next.next != null) {
            p.next = p.next.next;
            q.next = q.next.next;
            p = p.next;
            q = q.next;
        }
        p.next = null;  // 注意封尾操作，详细的拆分 参见 328题
        return newNode;
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node p = head, newNode, q;
        // 交叉排列
        while (p != null) {
            newNode = new Node(p.val, null, null);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }
        // 赋值新节点的 random
        p = head;
        while (p != null) {
            p.next.random = (p.random != null ? p.random.next : null);
            p = p.next.next;
        }
        // 拆开链表
        p = head;
        newNode = p.next;
        while (p.next != null && p.next.next != null) {
            q = p.next;
            p.next = q.next;
            p = p.next;
            q.next = p.next;
        }
        p.next = null;  // 注意封尾操作，详细的拆分参见 328题
        return newNode;
    }
    
    public Node createList() {
        Node node2 = new Node(2, null, null);
        node2.random = node2;
        Node node1 = new Node(1, node2, node2);
        return node1;
    }

    public static void main(String[] args) {
        P138RandomPointerListCopy p138 = new P138RandomPointerListCopy();
        Node head = p138.createList();
        Node head2 = p138.copyRandomList(head);
    }
}
