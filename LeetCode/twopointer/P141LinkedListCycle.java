package twopointer;

import util.ListNode;

/**
 * Title: 141. 环形链表
 * Desc:   给一个链表判断链表是否有环，你能用 O(1)（即，常量）内存解决此问题吗？
 * Created by Myth on 9/7/2019
 */
public class P141LinkedListCycle {
    // 使用哈希表，将每个指针地址存入，然后判断，空间复杂度 O（n）
    // 快慢指针 类似于两人跑步（慢指针每次1步，快指针每次2步），那么 环形部分/1 = 循环迭代的次数
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode node4 = new ListNode(-4);
        ListNode node3 = new ListNode(0, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(3, node2);
        node4.next = node2;
        P141LinkedListCycle p141 = new P141LinkedListCycle();
        System.out.println(p141.hasCycle(node1));
    }
}
