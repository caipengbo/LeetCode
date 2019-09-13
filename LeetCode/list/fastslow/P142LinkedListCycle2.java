package list.fastslow;

import util.ListNode;

/**
 * Title: 142. 环形链表 II
 * Desc: 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 * Created by Myth on 9/7/2019
 */
public class P142LinkedListCycle2 {
    // 是否有环，注意返回的不是入口节点哦, 实际上是获得的环形部分的数目！！！
    public ListNode hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
            if (slow == fast) return slow;
        }
        return null;
    }
    // 有环，找环的入口地点，然后同步走，就会走到环的入口处
    public ListNode detectCycle(ListNode head) {
        ListNode node1 = head;
        ListNode node2 = hasCycle(head);
        if (node2 == null) return null;
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    public static void main(String[] args) {
        ListNode node4 = new ListNode(-4);
        ListNode node3 = new ListNode(0, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(3, node2);
        node4.next = node2;
        ListNode node22 = new ListNode(2);
        ListNode node11 = new ListNode(1, node22);
        node22.next = node11;
        P142LinkedListCycle2 p142 = new P142LinkedListCycle2();
        System.out.println(p142.detectCycle(node11).val);
    }
}
