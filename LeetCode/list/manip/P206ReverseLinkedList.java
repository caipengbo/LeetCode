package list.manip;

import util.ListNode;
import util.ListUtil;

/**
 * Title:  206. 反转链表（使用迭代 和 递归）
 * Desc: 反转一个单链表
 * Created by Myth on 9/6/2019
 */
public class P206ReverseLinkedList {
    // 迭代（循环版本）
    public ListNode reverseList(ListNode head) {
        ListNode front = null, cur = head, back;
        while (cur != null) {
            back = cur.next;
            cur.next = front;
            front = cur;
            cur = back;
        }
        return front;
    }
    // 递归版本
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        P206ReverseLinkedList p206 = new P206ReverseLinkedList();
        ListUtil.prettyPrintLinkedList(p206.reverseList(ListUtil.stringToListNode("[1]")));
    }
}
