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
        int sum, carry = 0;
        while (p != null && q != null) {
            sum = (p.val + q.val + carry) > 9 ? (p.val + q.val + carry - 10) : (p.val + q.val + carry);
            carry = (p.val + q.val + carry) > 9 ? 1 : 0;
            if (r == null) {
                r = new ListNode(sum);
                head = r;
            } else {
                r.next = new ListNode(sum);
                r = r.next;
            }
            p = p.next;
            q = q.next;
        }
        while (p != null) {
            sum = (p.val + carry) > 9 ? (p.val + carry - 10) : (p.val + carry);
            carry = (p.val + carry) > 9 ? 1 : 0;
            r.next = new ListNode(sum);
            r = r.next;
            p = p.next;
        }
        while (q != null) {
            sum = (q.val + carry) > 9 ? (q.val + carry - 10) : (q.val + carry);
            carry = (q.val + carry) > 9 ? 1 : 0;
            r.next = new ListNode(sum);
            r = r.next;
            q = q.next;
        }
        if (carry != 0) {
            r.next = new ListNode(carry);
            r = r.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = ListUtil.stringToListNode("[2,4,3]");
        ListNode l2 = ListUtil.stringToListNode("[5,6,4]");

        ListNode l3 = ListUtil.stringToListNode("[1,8]");
        ListNode l4 = ListUtil.stringToListNode("[0]");
        P2AddTwoNumbers p2 = new P2AddTwoNumbers();
        // ListUtil.prettyPrintLinkedList(p2.addTwoNumbers(l1, l2));
        ListUtil.prettyPrintLinkedList(p2.addTwoNumbers(l3, l4));
    }
}
