package list.manip;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 92. 反转链表2
 * Desc: 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * Created by Myth-PC on 08/02/2020 in VSCode
 */
public class P92ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode oldLast = head, before = null, cur, after;
        
        for (int i = 1; i < m-1; i++) {
            oldLast = oldLast.next;
        }
        ListNode reverseLast = (m == 1 ? head : oldLast.next);
        cur = reverseLast;
        for (int i = m; i <= n; i++) {
            after = cur.next;
            cur.next = before;
            before = cur;
            cur = after;
        }
        reverseLast.next = cur;
        if (m == 1) return before;
        oldLast.next = before;
        return head;
    }
    // 设置DummyNode
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode oldLast = dummy, before = null, cur, after;
        
        for (int i = 1; i < m; i++) {
            oldLast = oldLast.next;
        }
        ListNode reverseLast = oldLast.next;
        cur = reverseLast;
        for (int i = m; i <= n; i++) {
            after = cur.next;
            cur.next = before;
            before = cur;
            cur = after;
        }
        reverseLast.next = cur;
        oldLast.next = before;
        return dummy.next;
    }
    public static void main(String[] args) {
        P92ReverseLinkedList2 p92 = new P92ReverseLinkedList2();
        ListNode head = ListUtil.stringToListNode("[1,2,3,4,5]");
        ListUtil.prettyPrintLinkedList(p92.reverseBetween2(head, 1, 2));
    }
}