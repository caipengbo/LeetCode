package list;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 2. 两数相加
 * Desc: https://leetcode-cn.com/problems/add-two-numbers/
 * Created by Myth on 9/4/2019
 */
public class P2AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2, r = null;
        ListNode head = null;
        int carry = 0;
        while (p != null && q != null) {
            if (r == null) {
                r = new ListNode((p.val + q.val + carry) / 10);
                head = r;
            } else {
                r.next = new ListNode((p.val + q.val + carry) / 10);
                r = r.next;
            }
            carry = (p.val + q.val + carry) % 10;
            p = p.next;
            q = q.next;
        }
        while (p != null) {
            r.next = new ListNode((p.val + carry) / 10);
            carry = (p.val + carry) % 10;
            r = r.next;
        }
        while (q != null) {
            r.next = new ListNode((q.val + carry) / 10);
            carry = (q.val + carry) % 10;
            r = r.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = ListUtil.stringToListNode("[2,4,3]");
        ListNode l2 = ListUtil.stringToListNode("[5,6,4]");
        P2AddTwoNumbers p2 = new P2AddTwoNumbers();
        ListUtil.prettyPrintLinkedList(p2.addTwoNumbers(l1, l2));
    }
}
