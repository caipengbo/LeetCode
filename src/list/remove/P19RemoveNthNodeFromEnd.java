package list.remove;

import util.ListNode;

/**
 * Title: 19. 删除链表的倒数第N个节点
 * Desc: 给定一个链表，删除链表的倒数第 n 个节点（n保证有效, 不会等于0 哦），并且返回链表的头结点。使用一趟扫描
 * Created by Myth on 9/11/2019
 */
public class P19RemoveNthNodeFromEnd {
    
    // 简化：使用dummy，不用pre指针，直接slow指向要删除的节点的前面位置,使用 n 递减计数
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        while (fast.next != null) {
            if (n <= 0) slow = slow.next;
            fast = fast.next;
            n--;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        int count = 0;
        ListNode fast = head, slow = head, pre = head;
        while (fast != null) {
            count++;
            if (count > n) {
                pre = slow;
                slow = slow.next;
            }
            fast = fast.next;
        }
        if (slow == head) {
            head = head.next;
        } else {
            pre.next = slow.next;
            slow.next = null;
        }
        return head;
    }
    

}
