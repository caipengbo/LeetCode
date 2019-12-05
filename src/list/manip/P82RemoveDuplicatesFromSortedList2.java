package list.manip;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 82. 删除排序链表中的重复元素 II
 * Desc: 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * Created by Myth-Lab on 10/9/2019
 */
public class P82RemoveDuplicatesFromSortedList2 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        int temp = (head.val == -1) ? -2 : -1;
        ListNode dummy = new ListNode(temp);
        // 确保pre与不重复的元素相连
        ListNode pre = dummy, p = head, q;
        while (p != null) {
            if (p.next != null && p.next.val == p.val) {
                q = p.next;
                while (q != null && q.val == p.val) q = q.next;
                p = q;
            } else {
                pre.next = p;
                pre = p;
                p = p.next;
            }
            if (p == null) pre.next = null;
        }
        return dummy.next;
    }
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, p = head;
        while (p != null) {
            if (p.next == null || p.next.val != p.val) {
                if (pre.next == p) pre = p;
                else pre.next = p.next;
            }
            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        P82RemoveDuplicatesFromSortedList2 p82 = new P82RemoveDuplicatesFromSortedList2();
        ListNode node = ListUtil.stringToListNode("[0]");
        ListUtil.prettyPrintLinkedList(p82.deleteDuplicates2(node));
    }
}
